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
        String tipoMensagem = mensagem.substring(0, 3);
        String valorCentavosStr = mensagem.substring(4, 12);
        double valorCentavos = Double.parseDouble(valorCentavosStr) / 100.0;
        String horaLocalTransacao = mensagem.substring(13, 18);
        String dataTransacao = mensagem.substring(19, 22);
        String redeTransmissora = mensagem.substring(23, 28);
        String numeroCartao = mensagem.substring(29, 40);
        String formaPag = mensagem.substring(41);


        for (Cartao cartao : cartoes) {
            if (cartao.getNumero().equals(numeroCartao)) {
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


    private static Cartao encontrarCartao(String numeroCartao) {
        for (Cartao cartao : cartoes) {
            if (cartao.getNumero().equals(numeroCartao)) {
                return cartao;
            }
        }
        return null;
    }

    private static synchronized String gerarNSU() {
        return String.format("", nsuCounter++);
    }

}
