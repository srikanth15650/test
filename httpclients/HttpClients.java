import com.example.restservice.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HttpClients {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest.Builder builder =  HttpRequest.newBuilder(new URI("https://postman-echo.com/get"));
        HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).GET().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).version(HttpClient.Version.HTTP_2)
                .GET().build();
        HttpRequest httpRequest1 = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get"))
                .headers().GET().build();
        HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get"))
                .header("key1","value1").header("key2","value2").GET().build();
        HttpRequest.newBuilder().timeout(Duration.of(10, ChronoUnit.SECONDS)).GET().build();
        HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).POST(HttpRequest.
                BodyPublishers.noBody()).build();
        HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get")).POST(
                HttpRequest.BodyPublishers.ofString("sample request Body")
        ).build();
        byte[] sampleData = "Sample Request Data".getBytes();

        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get"))
                .headers("CONTENT-TYPE","text/plain;charset=UTF-8").POST(
                        HttpRequest.BodyPublishers.ofInputStream(new Supplier<ByteArrayInputStream>(){
                            @Override
                            public ByteArrayInputStream get(){
                                return new ByteArrayInputStream(sampleData);
                            }
                        })
                )
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .header("CONTENT-TYPE","text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("src/test/resources/sample.txt"))).build();

        HttpClient httpclient = HttpClient.newHttpClient();
        HttpClient.newBuilder().build();
        httpclient.send(request, HttpResponse.BodyHandlers.ofString());

        HttpClient.newBuilder().proxy(ProxySelector.getDefault())
                .build().send(request, HttpResponse.BodyHandlers.ofString());

        HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS)
                .build().send(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response =  HttpClient.newBuilder().authenticator(new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("username",
                        "password".toCharArray());
            }
        }).build().send(request, HttpResponse.BodyHandlers.ofString());


        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture =
                HttpClient.newBuilder().build().sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        List<URI> targets = Arrays.asList(new URI(""),
                new URI(""));
        HttpClient  client = HttpClient.newHttpClient();
        targets.stream().map(target->
                client.sendAsync(
                        HttpRequest.newBuilder(target).GET().build() ,
                        HttpResponse.BodyHandlers.ofString()).
                        thenApply( response3 ->response3.body()) )
                .collect(Collectors.toUnmodifiableList());

        ObjectMapper objectMapper = new ObjectMapper();
        Todo todo = objectMapper.readValue(response.body(),Todo.class);



        CompletableFuture<HttpResponse<String>> response1 =
                HttpClient.newBuilder().executor(Executors.newFixedThreadPool(2))
                .build().sendAsync(request,HttpResponse.BodyHandlers.ofString());

        HttpClient.newBuilder().cookieHandler(new
                CookieManager(null,CookiePolicy.ACCEPT_NONE));
       CookieStore cookieStore =
               ((CookieManager)client.cookieHandler().get()).getCookieStore();
       cookieStore.getCookies().stream().map(a->a.getComment()
       +a.getCommentURL()+a.getDomain()+a.getName()+a.getMaxAge()+a.getVersion()+
               a.getPath());

       HttpRequest.newBuilder().uri(new URI(""))
               .version(HttpClient.Version.HTTP_2)
               .GET().build();

       HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());

        HttpClient client3 = HttpClient.newHttpClient();

        HttpRequest request4 = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .build();

        HttpResponse.BodyHandler<String> responseHandler = HttpResponse.BodyHandlers.ofString();


        HttpResponse.PushPromiseHandler<String> pushPromiseHandler2 =
                new HttpResponse.PushPromiseHandler<String>() {
                    @Override
                    public void applyPushPromise(HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                                                 Function<HttpResponse.BodyHandler<String>,
                                                         CompletableFuture<HttpResponse<String>>> acceptor) {
                        System.out.println("Push Promise Received: " + pushPromiseRequest.uri());

                        // Accept the push by providing a response body handler
                        acceptor.apply(HttpResponse.BodyHandlers.ofString());
                        System.out.println("Promise request: " + pushPromiseRequest.uri());
                        System.out.println("Promise request: " + pushPromiseRequest.headers());
                    }
                };

// Send request with PushPromiseHandler
        client.sendAsync(request, responseHandler, pushPromiseHandler2)
                .thenAccept(response5 -> System.out.println("Main Response: " + response5.body()));

    }
}
