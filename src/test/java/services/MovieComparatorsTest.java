package services;
import classes.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieComparatorsTest {
    @Test
    public void deveOrdenarPorAnoCorretamente() {
        Movie movie1 = new Movie("batman", "1990", null, null, null, null);
        Movie movie2 = new Movie("batman e robin", "1995", null, null, null, null);

        Comparator<Movie> comparator = MovieComparators.porAno();

        int resultado = comparator.compare(movie1, movie2);
        assertTrue(resultado < 0);
    }
    @Test
    public void deveOrdenarCorretamenteComAnoEmIntervalo(){
        Movie movie1 = new Movie("batman","1990-2010",null,null,null,null);
        Movie movie2 = new Movie("batman e robin","1995",null,null,null,null);

        Comparator<Movie> comparator = MovieComparators.porAno();

        int resultado = comparator.compare(movie1, movie2);
        assertTrue(resultado < 0);
    }

    @Test
    public void testeGerarHtml(){
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        List<Movie> movieList = new ArrayList<>();
        Movie movieTest1 = new Movie("Filme Teste","1995-2000",null,null,null,null);
        movieList.add(movieTest1);

        String resultHtml = htmlGenerator.retornHtml(movieList);

        assertTrue(resultHtml.contains("Filme Teste"));
        assertTrue(resultHtml.contains("1995-2000"));
    }
}
