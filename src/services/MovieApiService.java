package services;

import classes.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieApiService {
    List<Movie> procurarPalavraChave (String palavra) throws IOException, InterruptedException;
    Movie procurarPorID (String id) throws IOException, InterruptedException;
}
