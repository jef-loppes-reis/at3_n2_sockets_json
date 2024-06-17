package socketslibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        try{
            ServerSocket serverSocket = new ServerSocket(4000);
                       
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                String recebido = entrada.readObject().toString();    
                switch (recebido) {
                    case "1":
                        System.out.println("estou aqui!"); 
                        break;
                    default:
                        break;
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
