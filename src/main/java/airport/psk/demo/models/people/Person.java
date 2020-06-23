package airport.psk.demo.models.people;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Random;

@Data
public class Person {

    @Id
    private String id;
    private String name;
    private String surname;
    private LocalDate localDate;

    Person(String name, String surname, LocalDate localDate) {
        this.name = name;
        this.surname = surname;
        this.localDate = localDate;
    }

    public static Employer convertToEmployer(Person person) {
        Random r = new Random();
        return new Employer(person.getId(), person.getName(), person.getSurname(), person.getLocalDate(),
                r.nextInt(8000) + 2000, r.nextInt(2000), LocalDate.now());
    }
}
