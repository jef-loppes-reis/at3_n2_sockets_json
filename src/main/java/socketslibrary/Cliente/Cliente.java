package socketslibrary.Cliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 4000);
        Boolean continuar = true;

        while(continuar){
            ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(socket.getOutputStream());
           
            ObjectInputStream objectInputStream =
            new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Digite o que deseja fazer: ");
            System.out.println("1 - Listar os livros");
            System.out.println("2 - Alugar um livro");
            // System.out.println("3 - Devolver seus livros");
            System.out.println("4 - Sair");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    objectOutputStream.writeObject(opcao);
                    String recebido = objectInputStream.readObject().toString();
                    System.out.println(recebido);
                    break;
                case "2":
                    System.out.println("Digite o livro que quer pegar emprestado: ");
                    String escolhaDeLivro = scanner.nextLine();
                    objectOutputStream.writeObject(opcao);
                    objectOutputStream.writeObject(escolhaDeLivro);
                case "4":
                    continuar = false;
                default:
                    break;
            }
            System.out.println("-----------------------");   
        }
        socket.close();     
    }
}
