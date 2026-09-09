import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import classes.Movie;
import services.HtmlGenerator;
import services.MovieApiService;
import services.MovieComparators;
import services.OmdbService;

public class Main{
    public static void main(String[] args) {
        try {
            MovieApiService movieApiService = new OmdbService();
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            String palavraChave = "batman";

            List<Movie> encontrados = movieApiService.procurarPalavraChave(palavraChave);

            List<Movie> filmesEncontrados = new ArrayList<>();

            for (Movie filmes : encontrados) {
                Movie encontradosPorID = movieApiService.procurarPorID(filmes.getImdbID());

                filmesEncontrados.add(encontradosPorID);
            }

            Collections.sort(filmesEncontrados, MovieComparators.porAno()); // organizando os filmes por ano.

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