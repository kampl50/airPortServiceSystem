package airport.psk.demo.models.others;

import airport.psk.demo.enums.FlightClass;
import airport.psk.demo.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    private String id;
    private String name;
    private String surname;
    private String passportNumber;
    private String flightID;
    private String departureTime;
    private String nameOfAirport;
    private String nameOfCity;
    private FlightClass flightClass;
    private double price;
    private TicketStatus status;


    public Ticket(String name, String surname, String passportNumber, String flightID, String departureTime, String nameOfAirport, String nameOfCity, FlightClass flightClass, double price, TicketStatus status) {
        this.name = name;
        this.surname = surname;
        this.passportNumber = passportNumber;
        this.flightID = flightID;
        this.departureTime = departureTime;
        this.nameOfAirport = nameOfAirport;
        this.nameOfCity = nameOfCity;
        this.flightClass = flightClass;
        this.price = price;
        this.status = status;
    }
}


