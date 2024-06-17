package socketslibrary;

public class OpcaoDeLivro {
    private String autor;
    private String nome;
    private String genero;
    private int numeroDeExemplares;
    private int numeroDeExemplaresDisponiveis;

    public OpcaoDeLivro(String autor, String nome, String genero, int numeroDeExemplares, int numeroDeExemplaresDisponiveis) {
        this.setAutor(autor);
        this.setNome(nome);
        this.setGenero(genero);
        this.setNumeroDeExemplares(numeroDeExemplares);
        this.setNumeroDeExemplaresDisponiveis(numeroDeExemplaresDisponiveis);
    }

    public OpcaoDeLivro(String autor, String nome, String genero, int numeroDeExemplares) {
        this.setAutor(autor);
        this.setNome(nome);
        this.setGenero(genero);
        this.setNumeroDeExemplares(numeroDeExemplares);
        this.setNumeroDeExemplaresDisponiveis(numeroDeExemplares);
    }

    public String getAutor() {
        return autor;
    }

    public String getNome() {
        return nome;
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

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        String autorLinha =  "Autor: " + this.autor;
        String nomeLinha =  "Nome: " + this.nome;
        String generoLinha = "Genero" + this.genero;
        String numeroDeExemplaresLinha =  "N° de exemplares: " + this.numeroDeExemplares;
        String numeroDeExemplaresDisponiveisLinha = "N° de exemplares disponiveis: " + this.numeroDeExemplaresDisponiveis;


        return autorLinha + "\n" + 
                nomeLinha + "\n" + 
                generoLinha + "\n" + 
                numeroDeExemplaresLinha + "\n" + 
                numeroDeExemplaresDisponiveisLinha + "\n";
    }

    public boolean alugarExemplar(){
        if(this.numeroDeExemplaresDisponiveis > 0){
            this.numeroDeExemplaresDisponiveis -= 1;
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
