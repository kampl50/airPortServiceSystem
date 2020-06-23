package airport.psk.demo.repositories;

import airport.psk.demo.models.people.Employer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployerRepository extends MongoRepository<Employer, String> {
}
