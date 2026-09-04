import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Utilizando o Jackson, biblioteca que converte objetos Java em strings JSON e strings JSON em objetos Java.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie  {
    @JsonProperty("Title") // JsonProperty Ela permite você ter um nome "bonito", para dizer pro Jackson
    // "essa variavel corresponde a esse campo específico do JSON: no meu caso title
    private String title;
    @JsonProperty("Year") private String year;
    @JsonProperty("imdbID") private String imdbID; // O Api já está devolvendo o nome imdbID
    @JsonProperty("Type") private String type;
    @JsonProperty("Poster") private String poster;
    @JsonProperty("imdbRating")private String imdbRating;

    @JsonCreator // Metodo construtor do Jackson para "construir" o objeto do Movie
    public Movie(
        @JsonProperty("Title") String title,
        @JsonProperty("Year") String year,
        @JsonProperty("ImdbID") String imdbID,
        @JsonProperty("Type") String type,
        @JsonProperty("Poster") String poster,
        @JsonProperty("imdbRating") String imdbRating
        ){
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
        this.imdbRating = imdbRating;
    }
    public String getTitle() {
        return title;
    }

    public String getYear(){
        return year;
    }

    public String getType(){
        return type;
    }

    public String getPoster(){
        return poster;
    }

    public String getImdbID(){
        return imdbID;
    }
    public String getImdbRating(){
        return imdbRating;
    }
}
