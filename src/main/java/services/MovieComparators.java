package services;

import classes.Movie;

import java.util.Comparator;

public class MovieComparators {

    public static Comparator<Movie> porAno(){
        return Comparator.comparing(filme -> {String filmesAnos = filme.getYear(); // lambda para realizar o
            // metodo
            String[] partes = filmesAnos.split("\\D"); // \D significa "qualquer caractere que NÃO seja um dígito"
            String primeiraParte = partes[0];

            int anoFilme = Integer.parseInt(primeiraParte);

            return anoFilme;});
    }

}
