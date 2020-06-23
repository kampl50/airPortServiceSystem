package airport.psk.demo.models.dtoModels;

import airport.psk.demo.models.flightThings.AirPort;
import airport.psk.demo.models.flightThings.Crew;
import airport.psk.demo.models.flightThings.Plane;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO {


    private String departureTime;
    private AirPort arrivalPlace;
    private Crew crew;
    private Plane plane;

    public FlightDTO(String departureTime, AirPort arrivalPlace, Crew crew, Plane plane) {

        this.departureTime = departureTime;
        this.arrivalPlace = arrivalPlace;
        this.crew = crew;
        this.plane = plane;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public AirPort getArrivalPlace() {
        return arrivalPlace;
    }

    public Crew getCrew() {
        return crew;
    }

    public Plane getPlane() {
        return plane;
    }
}
