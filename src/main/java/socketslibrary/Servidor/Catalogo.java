package socketslibrary.Servidor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import socketslibrary.OpcaoDeLivro;

public class Catalogo {
    private List<OpcaoDeLivro> opcoesDeLivros = new ArrayList<OpcaoDeLivro>();
    private File arquivoLivrosJSON;

    public Catalogo(String pathLivrosJSON) throws IOException {
        this.arquivoLivrosJSON = new File(pathLivrosJSON);
        String jsonString = new String(Files.readAllBytes(this.arquivoLivrosJSON.toPath()));
        this.opcoesDeLivros = conversorLivrosFormatoJSONparaFormatoListaOpcaoDeLivro(jsonString);
        System.out.println(this);
    }

    public String toJSON() {
        String catalogoJSON = "{\n\t\"livros\":\n[\n";
        for (OpcaoDeLivro opcaoDeLivro: this.opcoesDeLivros) {
            catalogoJSON += opcaoDeLivro.toJSON() + "\n";
        }
        catalogoJSON += "]\n}";
        return catalogoJSON;
    }

    public List<OpcaoDeLivro> conversorLivrosFormatoJSONparaFormatoListaOpcaoDeLivro(String catalogoJSON) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();       
        JsonNode jsonNode = mapper.readTree(catalogoJSON);
        jsonNode = jsonNode.get("livros");

        List<OpcaoDeLivro> opcoesDeLivrosTemporaria = new ArrayList<OpcaoDeLivro>();

        if (jsonNode.isArray()) {
            int posicaoDaOpcao = 1;
            for (JsonNode node : jsonNode) {
                String titulo = node.get("titulo").asText();
                String autor = node.get("autor").asText();
                String genero = node.get("genero").asText();
                int exemplares = node.get("numeroDeExemplares").asInt();
                int numeroDeExemplaresDisponiveis = node.get("numeroDeExemplaresDisponiveis").asInt();
                // JsonNode pessoasQuePegaramEmprestadoJsonNode = node.get("pessoas_que_pesgaram_emprestado");
                // List<String> pessoasQuePegaramEmprestadoArrayList = mapper.readValue(pessoasQuePegaramEmprestadoJsonNode, ArrayList<String>);

                OpcaoDeLivro opcaoDeLivroAtual = new OpcaoDeLivro(
                posicaoDaOpcao,
                titulo, 
                autor,
                genero,
                exemplares,
                numeroDeExemplaresDisponiveis
                // pessoasQuePegaramEmprestadoArrayList
                );

                opcoesDeLivrosTemporaria.add(opcaoDeLivroAtual);
                posicaoDaOpcao++;
            }
        } 

        return opcoesDeLivrosTemporaria;
    }

    public Boolean alugarLivro(int opcaoEscolhida) throws StreamWriteException, DatabindException, IOException {
        for(OpcaoDeLivro opcaoDeLivro: this.opcoesDeLivros) {
            if(opcaoDeLivro.getPosicao() == opcaoEscolhida){
                Boolean result = opcaoDeLivro.alugarExemplar();
                this.salvarEmArquivo();
               
                return result;
            }
        }
        return false;
    }

    public Boolean devolverLivro(int opcaoEscolhida) throws StreamWriteException, DatabindException, IOException {
        for(OpcaoDeLivro opcaoDeLivro: this.opcoesDeLivros) {
            if(opcaoDeLivro.getPosicao() == opcaoEscolhida){
                Boolean result = opcaoDeLivro.devolverExemplar();
                this.salvarEmArquivo();
               
                return result;
            }
        }
        return false;
    }


    private void salvarEmArquivo() throws StreamWriteException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();  
        ObjectNode livrosNode = mapper.createObjectNode();
        livrosNode.putPOJO("livros", this.opcoesDeLivros);

        mapper.writerWithDefaultPrettyPrinter().writeValue(this.arquivoLivrosJSON, livrosNode);
    }
}
