import com.fasterxml.jackson.annotation.JsonProperty;

// Utilizando o Jackson, biblioteca que converte objetos Java em strings JSON e strings JSON em objetos Java.

public class Movie {
    @JsonProperty("Title") // JsonProperty Ela permite você ter um nome "bonito", para dizer pro Jackson
    // "essa variavel corresponde a esse campo específico do JSON: no meu caso title
    public String title;

    @JsonProperty("Year")
    public String year;

    public String imdbID; // O Api já está devolvendo o nome imdbID

    @JsonProperty("Type")
    public String type;

    @JsonProperty("Poster")
    public String poster;
}
