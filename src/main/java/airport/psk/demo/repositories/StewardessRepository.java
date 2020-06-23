package airport.psk.demo.repositories;

import airport.psk.demo.models.people.Stewardess;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StewardessRepository extends MongoRepository<Stewardess, String> {
}
