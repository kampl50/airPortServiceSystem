package airport.psk.demo.services;

import airport.psk.demo.models.flightThings.Flight;
import airport.psk.demo.models.others.Ticket;
import airport.psk.demo.repositories.FlightRepository;
import airport.psk.demo.repositories.TicketRepository;
import airport.psk.demo.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public List<Flight> getFlightsByCityAndDate(String city, LocalDateTime since, LocalDateTime down) {

        Stream<Flight> flightsByCity = flightRepository.findAll()
                .stream()
                .filter(flight -> flight.getArrivalPlace().getCity()
                        .toLowerCase()
                        .equals(city.toLowerCase()));

        List<Flight> flightsByCityAndTime = flightsByCity.filter(flight ->
                LocalDateTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).isAfter(since) &&
                        LocalDateTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).isBefore(down)).collect(Collectors.toList());

        return flightsByCityAndTime;
    }

    public void save(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void edit(Ticket ticketToUpdate){
        ticketRepository.deleteById(ticketToUpdate.getId());
        ticketRepository.save(ticketToUpdate);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public void updateSeats(Ticket ticket){
        MongoUtils.updateSeats(ticket,flightRepository);
    }
}
