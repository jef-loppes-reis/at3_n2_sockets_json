package socketslibrary;

import java.util.ArrayList;
import java.util.List;

public class OpcaoDeLivro {
    private int posicao;
    private String autor;
    private String titulo;
    private String genero;
    private int numeroDeExemplares;
    private int numeroDeExemplaresDisponiveis;

    public OpcaoDeLivro(int posicao, String autor, String titulo, String genero, int numeroDeExemplares, int numeroDeExemplaresDisponiveis) {
        this.setPosicao(posicao);
        this.setAutor(autor);
        this.setTitulo(titulo);
        this.setGenero(genero);
        this.setNumeroDeExemplares(numeroDeExemplares);
        this.setNumeroDeExemplaresDisponiveis(numeroDeExemplaresDisponiveis);
    }

    public OpcaoDeLivro(int posicao, String autor, String titulo, String genero, int numeroDeExemplares) {
        this.setPosicao(posicao);
        this.setAutor(autor);
        this.setTitulo(titulo);
        this.setGenero(genero);
        this.setNumeroDeExemplares(numeroDeExemplares);
        this.setNumeroDeExemplaresDisponiveis(numeroDeExemplares);
    }

    public int getPosicao() {
        return posicao;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroDeExemplares() {
        return numeroDeExemplares;
    }

    public String getGenero() {
        return genero;
    }

    public int getNumeroDeExemplaresDisponiveis() {
        return numeroDeExemplaresDisponiveis;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNumeroDeExemplares(int numeroDeExemplares) {
        this.numeroDeExemplares = numeroDeExemplares;
    }

    /**
     * A função setNumeroDeExemplaresDisponiveis foi posta como privada porque pode 
     * causar problemas de inconsistência nos dados. Por exemplo: há 4 exemplares. Uma
     * pessoa pega um exemplar emprestado diminuindo o numero de exemplares disponiveis
     * para 3. Daí, se usa um setNumeroDeExemplaresDisponiveis para 4 sem a pessoa
     * de fato ter devolvido. É algo um tanto perigoso.No mínimo, deve ser trabalhado 
     * com cuidado.
     */

     private void setNumeroDeExemplaresDisponiveis (int numeroDeExemplaresDisponiveis) {
        if(numeroDeExemplaresDisponiveis < 0){
            System.out.println("o numero de exemplares disponiveis deve ser maior ou igual a 0");
            return;
        }

        if(numeroDeExemplaresDisponiveis > this.numeroDeExemplares){
            System.out.println("o numero de exemplares disponiveis deve ser menor ou igual ao numero total de exemplares");
            return;
        }

        this.numeroDeExemplaresDisponiveis = numeroDeExemplaresDisponiveis;
     }

    public String toString(){
        String posicaoLinha = "Posicao: " + this.posicao;
        String autorLinha =  "Autor: " + this.autor;
        String tituloLinha =  "titulo: " + this.titulo;
        String generoLinha = "Genero: " + this.genero;
        String numeroDeExemplaresLinha =  "N° exemplares: " + this.numeroDeExemplares;
        String numeroDeExemplaresDisponiveisLinha = "N° de exemplares disponiveis: " + this.numeroDeExemplaresDisponiveis;


        return posicaoLinha + "\n" + 
                autorLinha + "\n" + 
                tituloLinha + "\n" + 
                generoLinha + "\n" + 
                numeroDeExemplaresLinha + "\n" + 
                numeroDeExemplaresDisponiveisLinha + "\n";
    }

    public String toJSON(){
        String autorFormatoJSON =  "\"autor\": " + "\"" + this.autor + "\"";
        String tituloFormatoJSON =  "\"titulo\": " + "\"" + this.titulo + "\"";
        String generoFormatoJSON = "\"genero\": " + "\"" + this.genero + "\"";
        String numeroDeExemplaresFormatoJSON =  "\"numero_de_exemplares\": " +  "\"" + this.numeroDeExemplares + "\"";
        String numeroDeExemplaresDisponiveisFormatoJSON = "\"numero_de_exemplares_disponiveis\": " 
        + "\"" + this.numeroDeExemplaresDisponiveis + "\"";


        return "{\n" + 
                "\t" + autorFormatoJSON + ",\n" + 
                "\t" + tituloFormatoJSON + ",\n" + 
                "\t" + generoFormatoJSON + ",\n" + 
                "\t" + numeroDeExemplaresFormatoJSON + ",\n" + 
                "\t" + numeroDeExemplaresDisponiveisFormatoJSON + "\n" + 
                "}";
    }

    public boolean alugarExemplar(){
        System.out.println("Aqui!" + this.toString());

        if(this.numeroDeExemplaresDisponiveis > 0){
            this.numeroDeExemplaresDisponiveis -= 1;
            System.out.println("Aqui!" + this.toString());

            return true;
        }
        else{
            return false;
        }

    }  
    public boolean devolverExemplar(){
        if(this.numeroDeExemplaresDisponiveis + 1 <= this.numeroDeExemplares){
            this.numeroDeExemplaresDisponiveis += 1;
            return true;
        }
        else{
            return false;
        }
    }
}
