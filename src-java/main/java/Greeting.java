
import lombok.Data;

@Data
public class Greeting {

    private String msg;
    private String name;

    public Greeting(String hello, String world) {
    }

    // standard getters, setters and constructor
}
