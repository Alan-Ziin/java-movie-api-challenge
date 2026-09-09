package services;
import classes.Movie;

import java.util.List;

public class HtmlGenerator {

    public String retornHtml(List<Movie> ResultadoComRating) {
        String htmlFilmes = "<html>" + "<head>" + "</head>" + "<body>";
        for (Movie filme : ResultadoComRating) {
             htmlFilmes = htmlFilmes + "<div id='cardFilme'>"
                    + "<h2>" + filme.getTitle() + "</h2>"
                    + "<img src='" + filme.getPoster() + "' alt='poster do filme'>"
                    + "<p>" + filme.getImdbRating() +" Ano do filme: "+filme.getYear() + "</p>"
                    + "</div>";
        }
        htmlFilmes = htmlFilmes + "</body>" + "</html>";
        return htmlFilmes;
    }
}
