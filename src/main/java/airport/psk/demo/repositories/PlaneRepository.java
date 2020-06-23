package airport.psk.demo.repositories;


import airport.psk.demo.models.flightThings.Plane;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PlaneRepository extends MongoRepository<Plane, String> {
}
