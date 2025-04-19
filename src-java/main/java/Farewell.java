
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Farewell {

    private String message;
    private Integer remainingMinutes;

    // standard getters, setters and constructor
}