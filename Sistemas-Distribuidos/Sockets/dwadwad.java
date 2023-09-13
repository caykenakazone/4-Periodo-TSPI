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
        // Inicialize alguns cartões com saldo
        cartoes.add(new Cartao("4012-3102-1845", "João da Silva", 1000.0));
        cartoes.add(new Cartao("5123-4567-8901", "Maria Oliveira", 500.0));
        cartoes.add(new Cartao("6010-1234-5678", "Pedro Santos", 750.0));

        ServerSocket serverSocket = new ServerSocket(2001);
        System.out.println("Servidor aguardando conexões...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            // Crie uma thread para lidar com a conexão do cliente
            Thread clientThread = new Thread(() -> {
                try (
                        DataInputStream entrada = new DataInputStream(socket.getInputStream());
                        DataOutputStream saida = new DataOutputStream(socket.getOutputStream())
                ) {
                    while (true) {
                        // Leia o cabeçalho da mensagem (4 caracteres)
                        byte[] cabecalhoBytes = new byte[4];
                        int bytesRead = entrada.read(cabecalhoBytes);
                        if (bytesRead != 4) {
                            System.out.println("Cabeçalho inválido: " + new String(cabecalhoBytes, 0, bytesRead));
                            continue; // Ignorar cabeçalhos inválidos
                        }
                        String cabecalho = new String(cabecalhoBytes);

                        // Agora que sabemos que a mensagem é válida, leia o restante da mensagem
                        byte[] mensagemBytes = new byte[1024]; // Tamanho máximo da mensagem (ajuste conforme necessário)
                        bytesRead = entrada.read(mensagemBytes);
                        String mensagem = new String(mensagemBytes, 0, bytesRead);
                        System.out.println("Mensagem recebida: " + cabecalho + mensagem);

                        // Processar a mensagem e obter a resposta
                        String resposta = processarMensagem(cabecalho + mensagem);

                        // Enviar a resposta de volta ao cliente
                        saida.write(resposta.getBytes());
                        System.out.println("Resposta enviada: " + resposta);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close(); // Feche a conexão com o cliente quando a comunicação for concluída ou houver um erro.
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

        // Resto do código de processamento...

        return "0200" + gerarNSU(); // Resposta 00 com NSU
    }

    private static Cartao encontrarCartao(String numeroCartao) {
        for (Cartao cartao : cartoes) {
            if (cartao.getNumero().equals(numeroCartao)) {
                return cartao;
            }
        }
        return null; // Cartão não encontrado
    }

    private static synchronized String gerarNSU() {
        return String.format("%04d", nsuCounter++);
    }
}
public class Mensagem {
    private String tipoMensagem;
    private String numeroCartao;
    private String valorTransacao;
    private String horaLocalTransacao;
    private String dataTransacao;
    private String redeTransmissora;
    private String numeroCartaoContinuacao;
    private String formaPagamento;

    public Mensagem(String tipoMensagem, String numeroCartao, String valorTransacao,
                           String horaLocalTransacao, String dataTransacao, String redeTransmissora,
                           String numeroCartaoContinuacao, String formaPagamento) {
        this.tipoMensagem = tipoMensagem;
        this.numeroCartao = numeroCartao;
        this.valorTransacao = valorTransacao;
        this.horaLocalTransacao = horaLocalTransacao;
        this.dataTransacao = dataTransacao;
        this.redeTransmissora = redeTransmissora;
        this.numeroCartaoContinuacao = numeroCartaoContinuacao;
        this.formaPagamento = formaPagamento;
    }

    public String construirMensagem() {
        // Use o caractere de ponto "." como delimitador entre os campos
        String delimitador = ".";

        // Construa a mensagem ISO 8583 completa com delimitadores
        return tipoMensagem + delimitador +
                numeroCartao + delimitador +
                valorTransacao + delimitador +
                horaLocalTransacao + delimitador +
                dataTransacao + delimitador +
                redeTransmissora + delimitador +
                numeroCartaoContinuacao + delimitador +
                formaPagamento;
    }
}
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteSocket {

    public static void main(String[] args) {
        try {
            // Conecta-se ao servidor em localhost (127.0.0.1) na porta 2001
            Socket socket = new Socket("127.0.0.1", 2001);

            // Crie fluxos de entrada e saída para comunicação com o servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

            // Construa a mensagem ISO 8583
            Mensagem mensagem = new Mensagem(
                    "0200",
                    "0412345678901234",
                    "000000000123",
                    "121212123456",
                    "0512",
                    "000000",
                    "401231021845",
                    "1"
            );

            // Obter a mensagem ISO 8583 completa
            String mensagemCompleta = mensagem.construirMensagem();

            // Converta a mensagem em uma matriz de bytes
            byte[] mensagemBytes = mensagemCompleta.getBytes();

            // Envie a mensagem para o servidor
            saida.write(mensagemBytes);

            // Aguarde a resposta do servidor
            byte[] respostaBytes = new byte[1024]; // Tamanho máximo da resposta (ajuste conforme necessário)
            int bytesRead = entrada.read(respostaBytes);
            String resposta = new String(respostaBytes, 0, bytesRead);
            System.out.println("Resposta do servidor: " + resposta);

            // Feche a conexão com o servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class Cartao {
    private String numero;
    private String nomeDoCliente;
    private double saldo;

    public Cartao(String numero, String nomeDoCliente, double saldo) {
        this.numero = numero;
        this.nomeDoCliente = nomeDoCliente;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public synchronized boolean realizarDebito(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }



}
public class Mensagem {
    private String tipoMensagem;
    private String numeroCartao;
    private String valorTransacao;
    private String horaLocalTransacao;
    private String dataTransacao;
    private String redeTransmissora;
    private String numeroCartaoContinuacao;
    private String formaPagamento;

    public Mensagem(String tipoMensagem, String numeroCartao, String valorTransacao,
                           String horaLocalTransacao, String dataTransacao, String redeTransmissora,
                           String numeroCartaoContinuacao, String formaPagamento) {
        this.tipoMensagem = tipoMensagem;
        this.numeroCartao = numeroCartao;
        this.valorTransacao = valorTransacao;
        this.horaLocalTransacao = horaLocalTransacao;
        this.dataTransacao = dataTransacao;
        this.redeTransmissora = redeTransmissora;
        this.numeroCartaoContinuacao = numeroCartaoContinuacao;
        this.formaPagamento = formaPagamento;
    }

    public String construirMensagem() {
        // Use o caractere de ponto "." como delimitador entre os campos
        String delimitador = ".";

        // Construa a mensagem ISO 8583 completa com delimitadores
        return tipoMensagem + delimitador +
                numeroCartao + delimitador +
                valorTransacao + delimitador +
                horaLocalTransacao + delimitador +
                dataTransacao + delimitador +
                redeTransmissora + delimitador +
                numeroCartaoContinuacao + delimitador +
                formaPagamento;
    }
}