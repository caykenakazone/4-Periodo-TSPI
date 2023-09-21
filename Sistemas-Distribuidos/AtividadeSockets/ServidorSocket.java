import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorSocket {

    private static List<Cartao> cartoes = new ArrayList<>();
    private static int nsuCounter = 1;

    public static void main(String[] args) throws IOException {
        cartoes.add(new Cartao("401231021845", "João da Silva", 1000.0));
        cartoes.add(new Cartao("512345678901", "Maria Oliveira", 500.0));
        cartoes.add(new Cartao("601012345678", "Pedro Santos", 750.0));

        ServerSocket serverSocket = new ServerSocket(2001);
        System.out.println("Servidor aguardando conexões...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());
            Thread clientThread = new Thread(() -> {
                try (
                        DataInputStream entrada = new DataInputStream(socket.getInputStream());
                        DataOutputStream saida = new DataOutputStream(socket.getOutputStream())
                ) {
                    while (true) {
                        String mensagem = entrada.readUTF();
                        System.out.println("Mensagem recebida: " + mensagem);
                        String resposta = processarMensagem(mensagem);
                        saida.writeUTF(resposta);
                        System.out.println("Resposta enviada: " + resposta);
                    }
                } catch (IOException e) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            clientThread.start();
        }
    }

    private static synchronized String processarMensagem(String mensagem) {
        String tipoMensagem = mensagem.substring(0, 4);
        String valorCentavosStr = mensagem.substring(5, 15);
        double valorCentavos = Double.parseDouble(valorCentavosStr) / 100.0;
        String horaLocalTransacao = mensagem.substring(15, 21);
        String dataTransacao = mensagem.substring(22, 25);
        String redeTransmissora = mensagem.substring(25, 31);
        String numeroCartao = mensagem.substring(31, 43);
        String formaPag = mensagem.substring(43);


        for (Cartao cartao : cartoes) {
            if (encontrarCartao(numeroCartao)) {
                if (valorCentavos > cartao.getSaldo()) {
                    return "5100" + "0000";
                } else {
                    cartao.realizarDebito(valorCentavos);
                    String nsu = gerarNSU();
                    return "0000" + nsu;
                }
            }

        }
        return "0500" + "0000";
    }


    private static boolean encontrarCartao(String numeroCartao) {
        for (Cartao cartao : cartoes) {
            if (cartao.getNumero().equals(numeroCartao)) {
                return true;
            }
        }
        return false;
    }

    private static synchronized String gerarNSU() {
        return String.format("%04d", nsuCounter++);
    }


}
