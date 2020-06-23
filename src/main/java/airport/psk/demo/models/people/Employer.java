package airport.psk.demo.models.people;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employer extends Person {

    private double salary;
    private double bonus;
    private LocalDate employmentDate;

    public Employer(String id, String name, String surname, LocalDate localDate, double salary, double bonus, LocalDate employmentDate) {
        super(name, surname, localDate);
        this.salary = salary;
        this.bonus = bonus;
        this.employmentDate = employmentDate;
        super.setId(id);
    }
}
