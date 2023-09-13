import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteSocket {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2001);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

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

            String mensagemCompleta = mensagem.construirMensagem();

            byte[] mensagemBytes = mensagemCompleta.getBytes();

            saida.write(mensagemBytes);

            byte[] respostaBytes = new byte[1024];
            int bytesRead = entrada.read(respostaBytes);
            String resposta = new String(respostaBytes, 0, bytesRead);
            System.out.println("Resposta do servidor: " + resposta);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
