package services;

import classes.Movie;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieComparators {

    public static Comparator<Movie> porAno(){
        return Comparator.comparing(filme -> {String filmesAnos = filme.getYear();
            String[] partes = filmesAnos.split("–");
            String primeiraParte = partes[0];
            int anoFilme = Integer.parseInt(primeiraParte);
            return anoFilme;});
    }

}
