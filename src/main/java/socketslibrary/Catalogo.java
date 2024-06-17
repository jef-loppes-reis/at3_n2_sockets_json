package socketslibrary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Catalogo {
    private List<OpcaoDeLivro> opcoesDeLivros;

    public Catalogo(String pathLivrosJSON) {
        this.opcoesDeLivros = new ArrayList<OpcaoDeLivro>();
        String jsonString;
        try {
            File arquivoCatalogo  = new File(pathLivrosJSON);
            jsonString = new String(Files.readAllBytes(arquivoCatalogo.toPath()));

            ObjectMapper mapper = new ObjectMapper();       
            try {
                JsonNode jsonNode = mapper.readTree(jsonString);
                // JsonNode arrayNode = jsonNode.get("livros");

                if (jsonNode.isArray()) {
                    for (JsonNode node : jsonNode) {
                        String titulo = node.get("titulo").asText();
                        String autor = node.get("autor").asText();
                        String genero = node.get("genero").asText();
                        int exemplares = node.get("numeroDeExemplares").asInt();

                        OpcaoDeLivro opcaoDeLivroAtual = new OpcaoDeLivro(titulo, 
                        autor,
                        genero,
                        exemplares);

                        this.opcoesDeLivros.add(opcaoDeLivroAtual);
                    }
                    System.out.println(this.opcoesDeLivros);
                }

            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }

    public void salvar(String pathLivrosJSON) {
        ObjectMapper mapper = new ObjectMapper();  
        File arquivoCatalogo  = new File(pathLivrosJSON);
       
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivoCatalogo, this.opcoesDeLivros);
        } catch (StreamWriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
