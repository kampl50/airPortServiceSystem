package airport.psk.demo.models.flightThings;


import airport.psk.demo.models.dtoModels.FlightDTO;
import airport.psk.demo.utils.Distance;
import airport.psk.demo.utils.MathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    private String id;
    private String departureTime;
    private AirPort departurePlace;
    private AirPort arrivalPlace;
    private Crew crew;
    private Plane plane;
    private int numberOfFreePlacesAll;
    private int numberOfFreePlacesInEconomyClass;
    private int numberOfFreePlacesInBusinessClass;
    private int numberOfFreePlacesInFirstClass;
    private double basePrice;

    Flight(String departureTime, AirPort arrivalPlace, Crew crew, Plane plane) {
        AirPort ourAirPort = new AirPort("Warsaw", "WAW", "PL", "Warsaw Chopin", 52.16, 20.96);
        this.departureTime = departureTime;
        this.departurePlace = ourAirPort;
        this.arrivalPlace = arrivalPlace;
        this.crew = crew;
        this.plane = plane;
        this.numberOfFreePlacesAll = plane.getNumberOfSeats();
        this.numberOfFreePlacesInEconomyClass = plane.getNumberOfSeatsInEconomyClass();
        this.numberOfFreePlacesInBusinessClass = plane.getNumberOfSeatsInBusinessClass();
        this.numberOfFreePlacesInFirstClass = plane.getNumberOfSeatsInFirstClass();
        this.basePrice = MathUtils.round(Distance.getDistance(arrivalPlace.getCity())/2,2);
    }

    public static Flight convertFromFlightDTO(FlightDTO flightDTO) {
        return new Flight(flightDTO.getDepartureTime(),
                flightDTO.getArrivalPlace(),
                flightDTO.getCrew(),
                flightDTO.getPlane());
    }
}
