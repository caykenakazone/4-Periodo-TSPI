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
        cartoes.add(new Cartao("4012-3102-1845", "João da Silva", 1000.0));
        cartoes.add(new Cartao("5123-4567-8901", "Maria Oliveira", 500.0));
        cartoes.add(new Cartao("6010-1234-5678", "Pedro Santos", 750.0));

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
                        byte[] cabecalhoBytes = new byte[4];
                        int bytesRead = entrada.read(cabecalhoBytes, 0, 4);
                        if (bytesRead != 4) {
                            System.out.println("Cabeçalho inválido: " + new String(cabecalhoBytes, 0, bytesRead));
                            continue;
                        }
                        String cabecalho = new String(cabecalhoBytes);


                        byte[] mensagemBytes = new byte[1024];
                        bytesRead = entrada.read(mensagemBytes);
                        String mensagem = new String(mensagemBytes, 0, bytesRead);
                        System.out.println("Mensagem recebida: " + cabecalho + mensagem);
                        String resposta = processarMensagem(cabecalho + mensagem);
                        saida.write(resposta.getBytes());
                        System.out.println("Resposta enviada: " + resposta);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            clientThread.start();
        }
    }

    private static synchronized String processarMensagem(String mensagem) {
        String tipoMensagem = mensagem.substring(0, 4);
        String numeroCartao = mensagem.substring(4, 19);
        String valorCentavosStr = mensagem.substring(19, 31);
        String horaLocalTransacao = mensagem.substring(31, 37);
        String dataTransacao = mensagem.substring(37, 41);
        String redeTransmissora = mensagem.substring(41, 47);


        return "0200" + gerarNSU();
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
        return String.format("%04d", nsuCounter++);
    }
}
