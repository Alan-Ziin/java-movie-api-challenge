import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main{
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient(); // Criando o objeto httpclient

        HttpRequest request = HttpRequest.newBuilder() // Construindo um builder basicamente
                // uma "Carta" para a URL de busca
                .uri(URI.create("http://www.omdbapi.com/?s=batman&apikey=85c1f01e"))
                .GET()
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // o segundo argumento pega os bytes da resposta e os traduz para uma String.

            ObjectMapper mapper = new ObjectMapper();

            MovieSearchResponse filmesListados = mapper.readValue(response.body(), MovieSearchResponse.class); // chama um método de
            // leitura, passando o JSON (texto) e dizendo pra qual classe ele deve converter, no meu caso Class MovieSearchResponse

            for (Movie tituloFilmes : filmesListados.search)
                System.out.println(tituloFilmes.title);
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}