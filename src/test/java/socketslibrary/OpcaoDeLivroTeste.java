package socketslibrary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class OpcaoDeLivroTeste {
    @Test
    @DisplayName("deveria ser capaz de alugar um livro quando há um disponivel")
    void alugarExemplar_retornarVerdadeiroQuandoHaLivroDisponivel() {
        int numeroDeExemplaresDisponiveis = 1;
        OpcaoDeLivro opcaoDeLivro = new OpcaoDeLivro(1, "autor", "nome", "genero", numeroDeExemplaresDisponiveis);
        assertTrue(opcaoDeLivro.alugarExemplar());
        assertEquals(numeroDeExemplaresDisponiveis - 1, opcaoDeLivro.getNumeroDeExemplaresDisponiveis());
    } 

    @Test
    @DisplayName("não deveria ser capaz de alugar um livro quando não há um disponivel")
    void alugarExemplar_retornarFalsoQuandoNaoHaLivroDisponivel() {
        OpcaoDeLivro opcaoDeLivro = new OpcaoDeLivro(2, "autor", "nome", "genero", 0);
        assertFalse(opcaoDeLivro.alugarExemplar());
        assertEquals(0, opcaoDeLivro.getNumeroDeExemplaresDisponiveis());
    } 

    @Test
    @DisplayName("deveria ser capaz de devolver um exemplar")
    void devolverExemplar_returnTrue_usarOMetodoQuandoOsLivrosDisponiveisSaoMenoresQueONumeroTotalDeLivros() {
        int numeroTotalDeExemplares = 3;
        OpcaoDeLivro opcaoDeLivro = new OpcaoDeLivro(3, "autor", "nome", "genero", numeroTotalDeExemplares);
        opcaoDeLivro.alugarExemplar();
        assertTrue(opcaoDeLivro.devolverExemplar());
        assertEquals(numeroTotalDeExemplares, opcaoDeLivro.getNumeroDeExemplaresDisponiveis());
        //espero que o numero de exemplares disponíveis tenha diminuído em 1
    } 

    @Test
    @DisplayName("não deveria ser capaz de devolver um exemplar")
    void devolverExemplar_returnFalse_tentarUsarOMetodoQuandoTodosOsLivrosEstaoDisponiveis() {
        int numeroTotalDeExemplares = 3;
        OpcaoDeLivro opcaoDeLivro = new OpcaoDeLivro(4, "autor", "nome", "genero", numeroTotalDeExemplares);
        opcaoDeLivro.alugarExemplar();
        opcaoDeLivro.devolverExemplar();
        assertFalse(opcaoDeLivro.devolverExemplar());
        assertEquals(numeroTotalDeExemplares, opcaoDeLivro.getNumeroDeExemplaresDisponiveis());
        //espero que o numero de exemplares disponíveis tenha diminuído em 1
    } 
}
