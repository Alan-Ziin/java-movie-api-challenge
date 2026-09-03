import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MovieSearchResponse {
    @JsonProperty("Search")
    public List<Movie> search;

    @JsonProperty("totalResults")
    public String totalResults;

    @JsonProperty("Response")
    public String response;
}
