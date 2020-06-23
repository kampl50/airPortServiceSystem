package airport.psk.demo.models.people;


import airport.psk.demo.enums.Availability;
import airport.psk.demo.repositories.StewardessRepository;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
public class Stewardess extends Person {

    private int experienceInYear;
    private String preferPlace;
    private Availability availability;

    public Stewardess(String name, String surname, LocalDate localDate, int experienceInYear, String preferPlace) {
        super(name, surname, localDate);
        this.experienceInYear = experienceInYear;
        this.preferPlace = preferPlace;
        this.availability = Availability.AVAILABLE;
    }

    public static List<String> getBestStewardesses(String city, StewardessRepository stewardessRepository) {
        List<Stewardess> allStewardesses = stewardessRepository.findAll();
        return findStewardessesByCities(city, allStewardesses);
    }

    private static List<String> findStewardessesByCities(String city, List<Stewardess> stewardesses) {
        List<String> bestStewardessesId = new LinkedList<>();

        for (Stewardess stewardess : stewardesses) {
            if (stewardess.getPreferPlace().equals(city)) {
                bestStewardessesId.add(stewardess.getId());
            }
        }
        return bestStewardessesId;
    }
}
