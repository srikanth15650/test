
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    //...
    public String getAlbumList() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "https://jsonplaceholder.typicode.com/albums";


        RestTemplate restTemplate = new RestTemplate();//
        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class));
    }

    public String getAlbumList2() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "http://localhost:1234/not-real";

        RestTemplate restTemplate = new RestTemplate();//
        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getDefaultAlbumList());
    }

    public  String getDefaultAlbumList(){
        return null;
    }
}