package airport.psk.demo.services;


import airport.psk.demo.models.flightThings.AirPort;
import airport.psk.demo.models.flightThings.Flight;
import airport.psk.demo.models.flightThings.MostVisitedPlaces;
import airport.psk.demo.models.flightThings.Plane;
import airport.psk.demo.models.others.Weather;
import airport.psk.demo.models.people.AirLinePilot;
import airport.psk.demo.models.people.Stewardess;
import airport.psk.demo.repositories.*;
import airport.psk.demo.utils.Distance;
import airport.psk.demo.utils.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightPlannerService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private MostVisitedPlacesRepository mostVisitedPlacesRepository;

    @Autowired
    private AirLinePilotRepository airLinePilotRepository;

    @Autowired
    StewardessRepository stewardessRepository;

    @Autowired
    PlaneRepository planeRepository;

    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    public void saveSte(Stewardess flight) {
        stewardessRepository.save(flight);
    }

    public void savePilots(AirLinePilot flight) {
        airLinePilotRepository.save(flight);
    }

    public void savePlane(Plane flight) {
        planeRepository.save(flight);
    }

    public void saveMost(MostVisitedPlaces flight) {
        mostVisitedPlacesRepository.save(flight);
    }

    public void deleteFlight(String id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<AirLinePilot> getAllPilots() {
        return airLinePilotRepository.findAll();
    }

    public List<String> getBestPilots(String city, String planeMark) {
        return AirLinePilot.getBestPilots(city, planeMark, this.airLinePilotRepository);
    }

    public List<Stewardess> getAllStewardesses() {
        return stewardessRepository.findAll();
    }

    public List<String> getStewardessesByCity(String city) {
        return Stewardess.getBestStewardesses(city, this.stewardessRepository);
    }

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public List<MostVisitedPlaces> getAllMostVisitedPlaces() {
        return mostVisitedPlacesRepository.findAll();
    }

    public Weather getWeather(String city) {
        return Weather.getWeatherFromJSON(city);
    }

    public String bestPlaneToFlight(String city) {
        return Plane.getBestPlaneToFlight(city, planeRepository);
    }

    public List<AirPort> getAirPorts(String cityName) {
        return AirPort.getAirPortsFromJSON(cityName);
    }

    public double getDistance(String city) {
        return Distance.getDistance(city);
    }

   public void increasePopularity(String city) {
       MongoUtils.incrementPopularity(city, mostVisitedPlacesRepository);
    }

    public AirLinePilot getPilotById(String id){
        return airLinePilotRepository.findById(id).get();
    }

    public Plane getPlaneById(String id){
        return planeRepository.findById(id).get();
    }

    public Stewardess getStewardessById(String id){
        return stewardessRepository.findById(id).get();
    }

    public void edit(Flight flightToUpdate) {
        flightRepository.deleteById(flightToUpdate.getId());
        flightRepository.save(flightToUpdate);
    }

    public void editStewardesses(Stewardess stewardess) {
        stewardessRepository.deleteById(stewardess.getId());
        stewardessRepository.save(stewardess);
    }

    public void editPilot(AirLinePilot airLinePilot) {
        airLinePilotRepository.deleteById(airLinePilot.getId());
        airLinePilotRepository.save(airLinePilot);
    }

    public void editPlane(Plane plane) {
        planeRepository.deleteById(plane.getId());
        planeRepository.save(plane);
    }

    public void deleteStewardesses(String id){
        stewardessRepository.deleteById(id);
    }

    public void deletePilot(String id){
        airLinePilotRepository.deleteById(id);
    }

    public void deletePlane(String id){
        planeRepository.deleteById(id);
    }

}
