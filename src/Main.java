import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient(); // Criando o objeto httpclient

        HttpRequest request = HttpRequest.newBuilder() // Construindo um builder basicamente
                // uma "Carta" para a URL de busca
                .uri(URI.create("http://www.omdbapi.com/?s=batman&apikey=85c1f01e"))
                .GET()
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body: " + response.body());
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }


    }
}