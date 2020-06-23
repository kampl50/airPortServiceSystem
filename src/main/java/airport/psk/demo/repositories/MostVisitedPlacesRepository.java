package airport.psk.demo.repositories;


import airport.psk.demo.models.flightThings.MostVisitedPlaces;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MostVisitedPlacesRepository extends MongoRepository<MostVisitedPlaces, String> {
}
