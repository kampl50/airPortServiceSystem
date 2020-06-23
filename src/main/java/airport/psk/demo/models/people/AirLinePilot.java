package airport.psk.demo.models.people;

import airport.psk.demo.enums.Availability;
import airport.psk.demo.repositories.AirLinePilotRepository;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
public class AirLinePilot extends Person {

    private int experienceInYear;
    private String preferPlace;
    private String preferPlaneMark;
    private Availability availability;

    public AirLinePilot(String name, String surname, LocalDate localDate, int experienceInYear, String preferPlace, String preferPlaneMark) {
        super(name, surname, localDate);
        this.experienceInYear = experienceInYear;
        this.preferPlace = preferPlace;
        this.preferPlaneMark = preferPlaneMark;
        this.availability = Availability.AVAILABLE;
    }

    public static List<String> getBestPilots(String city, String planeMark, AirLinePilotRepository repository) {
        List<AirLinePilot> allPilots = repository.findAll();
        return getBestPilotsByCityAndPlaneMark(city, planeMark, allPilots);
    }

    private static List<String> getBestPilotsByCityAndPlaneMark(String city, String planeMark, List<AirLinePilot> pilots) {
        List<String> bestPilotsId = new LinkedList<>();

        for (AirLinePilot pilot : pilots) {
            if (pilot.getPreferPlaneMark().equals(planeMark) || pilot.preferPlace.equals(city)) {
                bestPilotsId.add(pilot.getId());
            }
        }
        return bestPilotsId;
    }
}
