import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import classes.Movie;
import classes.MovieSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.HtmlGenerator;
import services.OmdbService;

public class Main{
    public static void main(String[] args) {
        try {
            OmdbService omdbService = new OmdbService();
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            String palavraChave = "batman";

            MovieSearchResponse encontrados = omdbService.procurarPalavraChave(palavraChave);

            List<Movie> filmesEncontrados = new ArrayList<>();

            for (Movie filmes : encontrados.search) {
                Movie encontradosPorID = omdbService.procurarPorID(filmes.getImdbID());

                filmesEncontrados.add(encontradosPorID);
            }

            String resultHtml = htmlGenerator.retornHtml(filmesEncontrados);
            try (FileWriter writer = new FileWriter("filmes.html")) {
                writer.write(resultHtml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}