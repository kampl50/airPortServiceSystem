package airport.psk.demo.repositories;


import airport.psk.demo.models.flightThings.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
    @Override
    List<Flight> findAll();

}
