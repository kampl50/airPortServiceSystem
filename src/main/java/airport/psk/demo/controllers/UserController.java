package airport.psk.demo.controllers;


import airport.psk.demo.enums.FlightClass;
import airport.psk.demo.enums.TicketStatus;
import airport.psk.demo.models.flightThings.Flight;
import airport.psk.demo.models.others.Ticket;
import airport.psk.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/flightsForUser")
    public List<Flight> getFlightsForUser(@RequestParam String city, String since, String down) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return userService.getFlightsByCityAndDate(city, LocalDateTime.parse(since, formatter), LocalDateTime.parse(down, formatter));
    }

    @PostMapping("/api/orderTicket")
    public void orderTicket(@RequestBody Ticket ticket) {
        userService.save(ticket);
        userService.updateSeats(ticket);
    }

    @PostMapping("/api/editTicket")
    public void editTicket(@RequestBody Ticket ticketToUpdate) {
        userService.edit(ticketToUpdate);
    }

    @GetMapping("/api/allTickets")
    public List<Ticket> editTicket() {
        return userService.getAllTickets();
    }
}
