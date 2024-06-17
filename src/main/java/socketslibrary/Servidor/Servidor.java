package socketslibrary.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import socketslibrary.OpcaoDeLivro;

public class Servidor {
    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        try{
            OpcaoDeLivro opc = new OpcaoDeLivro(0, null, null, null, 0);
            ServerSocket serverSocket = new ServerSocket(4000);
            Socket socket = serverSocket.accept();
            Catalogo catalogo = new Catalogo("data/livros.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());         

            while (continuar) {
                String recebido = objectInputStream.readObject().toString();    
                
                switch (recebido) {
                    case "1":
                        objectOutputStream.writeObject(String.valueOf(catalogo.paraCliente()));
                        break;
                    case "2":
                        int opcaoDeLivroEscolhido =  Integer.parseInt(objectInputStream.readObject().toString());
                        String resultado = catalogo.alugarLivro(opcaoDeLivroEscolhido);
                        System.out.println("Aqui: "+ resultado);
                        objectOutputStream.writeObject(resultado);
                        break;
                    case "3":
                        int opcaoDeLivroADevolver =  Integer.parseInt(objectInputStream.readObject().toString());
                        Boolean conseguiuDevolver = catalogo.devolverLivro(opcaoDeLivroADevolver);
                        if (conseguiuDevolver){
                            objectOutputStream.writeObject("Devolvido!");
                        } else {
                            objectOutputStream.writeObject("Não foi possível devolver esse livro.");
                        }
                    break;                    case "4":
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
