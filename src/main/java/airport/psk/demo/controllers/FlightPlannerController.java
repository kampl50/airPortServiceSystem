package airport.psk.demo.controllers;


import airport.psk.demo.models.dtoModels.FlightDTO;
import airport.psk.demo.models.flightThings.AirPort;
import airport.psk.demo.models.flightThings.Flight;
import airport.psk.demo.models.flightThings.MostVisitedPlaces;
import airport.psk.demo.models.flightThings.Plane;
import airport.psk.demo.models.others.Weather;
import airport.psk.demo.models.people.AirLinePilot;
import airport.psk.demo.models.people.Stewardess;
import airport.psk.demo.services.FlightPlannerService;
import airport.psk.demo.utils.AirPortsUtlis;
import airport.psk.demo.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class FlightPlannerController {

    @Autowired
    private FlightPlannerService flightPlannerService;


    @PostMapping("/api/createFlight")
    public void createFlight(@RequestBody FlightDTO flightDTO) {
        Flight flight = Flight.convertFromFlightDTO(flightDTO);
        flightPlannerService.save(flight);
        flightPlannerService.increasePopularity(flightDTO.getArrivalPlace().getCity());
        MongoUtils.updateAvailability(flightDTO);
    }

    @PostMapping("/api/editFlight")
    public void editFlight(@RequestBody FlightDTO flightToUpdate) {
        Flight flight = Flight.convertFromFlightDTO(flightToUpdate);
        flightPlannerService.edit(flight);
    }

    @PostMapping("/api/deleteFlight")
    public void deleteFlight(@RequestParam String id) {
        flightPlannerService.deleteFlight(id);
    }

    @GetMapping("/api/flights")
    public List<Flight> getFlights() {
        return flightPlannerService.getAllFlights();
    }

    @GetMapping("/api/distance")
    public double getDistanceBetweenCities(@RequestParam String city) {
        return flightPlannerService.getDistance(city);
    }

    @GetMapping("/api/bestPlane")
    public String getBestPlane(@RequestParam String city) {
        return flightPlannerService.bestPlaneToFlight(city);
    }

    @GetMapping("/api/weather")
    public Weather getWeather(@RequestParam String city) {
        return flightPlannerService.getWeather(city);
    }

    @GetMapping("/api/airPorts")
    public List<AirPort> getAirPorts(@RequestParam String city) {
        return AirPortsUtlis.get(city);
    }

    @GetMapping("/api/bestPilots")
    public List<String> getPilotsByCityAndPlaneMark(@RequestParam String city, @RequestParam String planeMark) {
        return flightPlannerService.getBestPilots(city, planeMark);
    }

    @GetMapping("/api/bestStewardesses")
    public List<String> getStewardessesByCity(@RequestParam String city) {
        return flightPlannerService.getStewardessesByCity(city);
    }

    @GetMapping("/api/mostVisitedPlaces")
    public List<MostVisitedPlaces> allMostVisitedPlaces() {
        return flightPlannerService.getAllMostVisitedPlaces();
    }

    @PostMapping("/api/deleteStewardesses")
    public void deleteStewardesses(@RequestParam String id) {
        flightPlannerService.deleteStewardesses(id);
    }

    @PostMapping("/api/deletePilot")
    public void deletePilot(@RequestParam String id) {
        flightPlannerService.deletePilot(id);
    }

    @PostMapping("/api/deletePlane")
    public void deletePlane(@RequestParam String id) {
        flightPlannerService.deletePlane(id);
    }

    @PostMapping("/api/editStewardesses")
    public void editStewardesses(@RequestBody Stewardess stewardess) {
        flightPlannerService.editStewardesses(stewardess);
    }

    @PostMapping("/api/editPilot")
    public void editPilot(@RequestBody AirLinePilot airLinePilot) {
        flightPlannerService.editPilot(airLinePilot);
    }

    @PostMapping("/api/editPlane")
    public void editPlane(@RequestBody Plane plane) {
        flightPlannerService.editPlane(plane);
    }

}
