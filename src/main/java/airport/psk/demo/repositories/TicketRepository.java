package airport.psk.demo.repositories;

import airport.psk.demo.models.others.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket,String> {
}
