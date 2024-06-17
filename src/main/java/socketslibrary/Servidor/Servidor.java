package socketslibrary.Servidor;

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
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());         

            while (continuar) {
                String recebido = objectInputStream.readObject().toString();    
                
                switch (recebido) {
                    case "1":
                        objectOutputStream.writeObject(String.valueOf(catalogo.toJSON()));
                        break;
                    case "2":
                        int opcaoDeLivroEscolhido =  Integer.parseInt(objectInputStream.readObject().toString());
                        Boolean conseguiuAlugar = catalogo.alugarLivro(opcaoDeLivroEscolhido);
                        if (conseguiuAlugar){
                            objectOutputStream.writeObject("alugado!");
                        } else {
                            objectOutputStream.writeObject("Não foi possível alugar esse livro.");
                        }
                        break;
                    case "3":
                        System.out.println("aqui");
                        objectOutputStream.writeObject("aqui 3");
                    case "4":
                        continuar = false;
                        break;
                    default:
                        objectOutputStream.writeObject("Opção inválida!");
                        break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
