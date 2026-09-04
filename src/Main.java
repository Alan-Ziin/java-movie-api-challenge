import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main{
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient(); // Criando o objeto httpclient

        HttpRequest request = HttpRequest.newBuilder() // Construindo um builder basicamente
                // uma "Carta" para a URL de busca
                .uri(URI.create("http://www.omdbapi.com/?s=batman&apikey=85c1f01e"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // o segundo argumento pega os bytes da resposta e os traduz para uma String.

            ObjectMapper mapper = new ObjectMapper();

            MovieSearchResponse filmesListados = mapper.readValue(response.body(), MovieSearchResponse.class); // chama um método de
            // leitura, passando o JSON (texto) e dizendo pra qual classe ele deve converter, no meu caso Class MovieSearchResponse
            List<Movie> ResultadoComRating = new ArrayList<>(); // lista nova e vazia, vai guardar os filmes "completos", já com nota
            for (Movie tituloFilmes : filmesListados.search) { // percorre cada filme da busca que é: (&s=), um por vez

                HttpClient clientRating = HttpClient.newHttpClient(); // novo client, só pra essa segunda chamada por ID

                HttpRequest requestRating = HttpRequest.newBuilder()
                        // monta a URL de busca por ID (&i=), usando o imdbID do filme atual do loop
                        .uri(URI.create("http://www.omdbapi.com/?i=" + tituloFilmes.getImdbID() + "&apikey=85c1f01e"))
                        .GET()
                        .build();
                try {
                    HttpResponse<String> responseRating = clientRating.send(requestRating, HttpResponse.BodyHandlers.ofString());
                    // esse JSON de retorno é de UM filme só (não vem dentro de "Search": [...] como antes)

                    Movie filmesListadosComRating = mapper.readValue(responseRating.body(), Movie.class);
                    // deserializa direto pra Movie, já que o JSON aqui representa um filme único

                    ResultadoComRating.add(filmesListadosComRating); // adicionando na lista nova

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String htmlFilmes = "<html>" + "<head>" + "</head>" + "<body>";
            for (Movie filme : ResultadoComRating){
                htmlFilmes = htmlFilmes + "<div id='cardFilme'>"
                        + "<h2>" + filme.getTitle() + "</h2>"
                        + "<img src='" + filme.getPoster() + "' alt='poster do filme'>"
                        + "<p>" + filme.getImdbRating() + "</p>"
                        + "</div>";
            }
            htmlFilmes = htmlFilmes + "</body>" + "</html>";
            try (FileWriter writer = new FileWriter("filmes.html")){
                writer.write(htmlFilmes);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}