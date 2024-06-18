package socketslibrary.Cliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private String[] idsLivrosQuePegouEmprestado;
    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 4000);
        String mensagemAoCliente;
        Boolean continuar = true;
        ObjectOutputStream objectOutputStream =
         new ObjectOutputStream(socket.getOutputStream());
           
        ObjectInputStream objectInputStream =
         new ObjectInputStream(socket.getInputStream());

        while(continuar){
           
            
            System.out.println("Digite o que deseja fazer: ");
            System.out.println("1 - Listar os livros");
            System.out.println("2 - Alugar um livro");
            System.out.println("3 - Devolver seus livros");
            System.out.println("4 - Sair");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    objectOutputStream.writeObject(opcao);
                    mensagemAoCliente = objectInputStream.readObject().toString();
                    break;
                case "2":
                    System.out.println("Digite o livro que quer pegar emprestado: ");
                    String escolhaDeLivro = scanner.nextLine();
                    objectOutputStream.writeObject(opcao);
                    objectOutputStream.writeObject(escolhaDeLivro);
                    mensagemAoCliente = objectInputStream.readObject().toString();
                case "3":
                    System.out.println("Digite o livro que quer devolver: ");
                    String livroEscolhidoParaDevolver = scanner.nextLine();
                    objectOutputStream.writeObject(opcao);
                    objectOutputStream.writeObject(livroEscolhidoParaDevolver);
                    mensagemAoCliente = objectInputStream.readObject().toString();
                case "4":
                    mensagemAoCliente= "Fim do programa";
                    continuar = false;
                default:
                    mensagemAoCliente= "Opcao invalida";
                    break;

            }
            System.out.println(mensagemAoCliente);

            System.out.println("-----------------------");   
        }
        socket.close();     
    }
}
