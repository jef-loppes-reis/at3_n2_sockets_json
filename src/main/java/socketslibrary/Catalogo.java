package socketslibrary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Catalogo {
    private List<OpcaoDeLivro> opcoesDeLivros = new ArrayList<OpcaoDeLivro>();
    private File arquivoLivrosJSON;

    public Catalogo(String pathLivrosJSON) throws IOException {
        this.arquivoLivrosJSON = new File(pathLivrosJSON);
        String jsonString = new String(Files.readAllBytes(this.arquivoLivrosJSON.toPath()));
        this.opcoesDeLivros = conversorLivrosFormatoJSONparaFormatoListaOpcaoDeLivro(jsonString);
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
            for (JsonNode node : jsonNode) {
                String titulo = node.get("titulo").asText();
                String autor = node.get("autor").asText();
                String genero = node.get("genero").asText();
                int exemplares = node.get("numero_de_exemplares").asInt();

                OpcaoDeLivro opcaoDeLivroAtual = new OpcaoDeLivro(titulo, 
                autor,
                genero,
                exemplares);

                opcoesDeLivrosTemporaria.add(opcaoDeLivroAtual);
            }
        }
        return opcoesDeLivrosTemporaria;
    }

}
