
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@RedisHash("Student")
public class Student implements Serializable {

    public Student(int eng2015001, String johnDoe, Gender gender, String i) {
        this.id = i;
        this.gender =gender;
        this.name = johnDoe;
        this.grade = eng2015001;
    }

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;
    // ...
}
