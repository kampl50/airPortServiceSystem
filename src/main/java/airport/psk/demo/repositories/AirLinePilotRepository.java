package airport.psk.demo.repositories;

import airport.psk.demo.models.people.AirLinePilot;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AirLinePilotRepository extends MongoRepository<AirLinePilot, String> {
}
