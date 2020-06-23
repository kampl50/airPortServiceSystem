package airport.psk.demo.models.flightThings;


import airport.psk.demo.models.dtoModels.EmployerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Crew {

    private List<EmployerDTO> airLinePilots;
    private List<EmployerDTO> stewardesses;

    public Crew(List<EmployerDTO> airLinePilots, List<EmployerDTO> stewardesses) {
        this.airLinePilots = airLinePilots;
        this.stewardesses = stewardesses;
    }
}
