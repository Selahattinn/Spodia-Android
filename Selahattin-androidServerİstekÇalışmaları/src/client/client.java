package aa;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;



public class client {
    public static void main(String[] args) throws IOException, InterruptedException  {
    	String uri ="http://127.0.0.1:8000/login";
    	HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uri))
              .header("name", "selahattin")
              .header("password", "asdqwezxc")
              .build();

        HttpResponse<String> response =
              client.send(request, BodyHandlers.ofString());
    	request.headers().firstValue("status");
    	
        System.out.println(response.headers().firstValue("status"));
        System.out.println("a");

    }
}



