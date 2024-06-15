import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        try (// Sobe o servidor.
        ServerSocket serverSocket = new ServerSocket(4000)) {
            // Enquanto o cliente nao se conectar.
            Socket socket =  serverSocket.accept();
            // Print da conecxao do Cliente.
            System.out.println("CLiente conectou!");

            // Leitura da mensagem do Cliente.
            InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
            PrintStream saida = new PrintStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(inputReader);
            String x;
            while ((x = reader.readLine()) != null) {
                saida.println("Servidor: " + x);
            }
        }
    }
}
