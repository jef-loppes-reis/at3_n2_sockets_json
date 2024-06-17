package socketslibrary;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            Socket socket = new Socket("localhost", 4000);
            ObjectOutputStream saida =
            new ObjectOutputStream(socket.getOutputStream());
            System.out.println("-----------------------");
            System.out.println("Digite o que deseja fazer: ");
            System.out.println("1 - Listar os livros");
            // System.out.println("2 - Alugar um livro");
            // System.out.println("3 - Devolver seus livros");
            String option = scanner.nextLine();

            saida.writeObject(option);

            System.out.println("-----------------------");   
            socket.close();     
        }
    }
}
