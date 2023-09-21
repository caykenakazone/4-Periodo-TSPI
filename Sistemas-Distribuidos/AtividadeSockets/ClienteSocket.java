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

            Mensagem msg = new Mensagem("0200","00000000200","104426","0512","040104","401231021845","1");
            String msgP = msg.construirMensagem();
            saida.writeUTF(msgP);
            String resposta = entrada.readUTF();
            System.out.println("Resposta do servidor: " + resposta);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
