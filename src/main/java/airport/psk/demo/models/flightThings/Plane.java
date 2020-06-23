package airport.psk.demo.models.flightThings;


import airport.psk.demo.enums.Availability;
import airport.psk.demo.repositories.PlaneRepository;
import airport.psk.demo.utils.Distance;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Plane {
    @Id
    private String id;
    private String mark;
    private String model;
    private int numberOfSeats;
    private int numberOfSeatsInEconomyClass;
    private int numberOfSeatsInBusinessClass;
    private int numberOfSeatsInFirstClass;
    private double range;
    private Availability availability;

    public Plane(String mark, String model, int numberOfSeats, int numberOfSeatsInEconomyClass, int numberOfSeatsInBusinessClass, int numberOfSeatsInFirstClass, double range) {
        this.mark = mark;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.numberOfSeatsInEconomyClass = numberOfSeatsInEconomyClass;
        this.numberOfSeatsInBusinessClass = numberOfSeatsInBusinessClass;
        this.numberOfSeatsInFirstClass = numberOfSeatsInFirstClass;
        this.range = range;
        this.availability = Availability.AVAILABLE;
    }

    public static String getBestPlaneToFlight(String city, PlaneRepository planeRepository) {
        double distance = Distance.getDistance(city);
        List<Plane> planes = planeRepository.findAll();
        return findBestPlane(distance, planes);
    }

    private static String findBestPlane(double distance, List<Plane> planes) {
        String planeId = planes.get(0).id;
        double difference = planes.get(0).range - distance;
        for (int i = 0; i < planes.size() - 1; i++) {

            if(difference < 100){
                planeId = planes.get(i + 1).id;
                difference = planes.get(i + 1).range - distance;
            }

            if (difference > (planes.get(i + 1).range - distance)) {
                difference = planes.get(i + 1).range - distance;
                planeId = planes.get(i + 1).id;
            }
            if (difference < 100 && i == planes.size() - 2) {
                return "PLANE TO THIS FLIGHT NOT EXISTS";
            }
        }
        return planeId;
    }
}
