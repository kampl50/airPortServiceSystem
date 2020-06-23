package airport.psk.demo.models.flightThings;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MostVisitedPlaces {

    @Id
    private String id;
    private String cityName;
    private int popularity;
}
