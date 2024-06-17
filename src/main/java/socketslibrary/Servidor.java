package socketslibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        try{
            ServerSocket serverSocket = new ServerSocket(4000);
            Socket socket = serverSocket.accept();
            Catalogo catalogo = new Catalogo("data/livros.json");

            while (continuar) {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                String recebido = objectInputStream.readObject().toString();    
                
                switch (recebido) {
                    case "1":
                        System.out.println("ok");
                        objectOutputStream.writeObject(String.valueOf(catalogo.toJSON()));
                        break;
                    case "4":
                        continuar = false;
                        break;
                    default:
                        break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
