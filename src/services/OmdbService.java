package services;
import classes.Movie;
import classes.MovieSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OmdbService {
    ObjectMapper mapper = new ObjectMapper();

    public MovieSearchResponse procurarPalavraChave(String palavraChave) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.omdbapi.com/?s="+palavraChave+"&apikey=85c1f01e"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        MovieSearchResponse filmesListados = mapper.readValue(response.body(), MovieSearchResponse.class);
        return filmesListados;
    }

    public Movie procurarPorID(String imdbID) throws IOException, InterruptedException{
        HttpClient clientID = HttpClient.newHttpClient();

        HttpRequest requestID = HttpRequest.newBuilder()
                .uri(URI.create("http://www.omdbapi.com/?i="+imdbID+"&apikey=85c1f01e"))
                .GET()
                .build();

        HttpResponse<String> response = clientID.send(requestID,HttpResponse.BodyHandlers.ofString());

        Movie filmeID = mapper.readValue(response.body(), Movie.class);
        return filmeID;
    }
}
