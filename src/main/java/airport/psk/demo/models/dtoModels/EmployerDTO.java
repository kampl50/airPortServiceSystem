package airport.psk.demo.models.dtoModels;


import airport.psk.demo.enums.Employer;
import airport.psk.demo.models.people.AirLinePilot;
import airport.psk.demo.models.people.Person;
import airport.psk.demo.models.people.Stewardess;
import lombok.Data;

@Data
public class EmployerDTO {

    private String id;
    private Employer kindOfEmployer;

    public EmployerDTO(String id, Employer kindOfEmployer) {
        this.id = id;
        this.kindOfEmployer = kindOfEmployer;
    }

    public static EmployerDTO convertFromPerson(Person person) {
        if (person.getClass() == AirLinePilot.class) {
            AirLinePilot airLinePilot = (AirLinePilot) person;
            return new EmployerDTO(airLinePilot.getId(), Employer.PILOT);

        } else if (person.getClass() == Stewardess.class) {
            Stewardess stewardess = (Stewardess) person;
            return new EmployerDTO(stewardess.getId(), Employer.STEWARDESS);
        }
        return null;
    }
}
