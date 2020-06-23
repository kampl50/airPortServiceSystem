package airport.psk.demo.controllers;

import airport.psk.demo.models.flightThings.MostVisitedPlaces;
import airport.psk.demo.models.flightThings.Plane;
import airport.psk.demo.models.people.AirLinePilot;
import airport.psk.demo.models.people.Employer;
import airport.psk.demo.models.people.Stewardess;
import airport.psk.demo.services.AdministratorService;
import airport.psk.demo.services.FlightPlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministratorController {

    @Autowired
    FlightPlannerService flightPlannerService;

    @PostMapping("/createStewardess")
    public void addS(@RequestBody Stewardess stewardess) {
        flightPlannerService.saveSte(stewardess);
    }

    @PostMapping("/createPilot")
    public void addPil(@RequestBody AirLinePilot stewardess) {
        flightPlannerService.savePilots(stewardess);
    }

    @PostMapping("/createPlane")
    public void addPlan(@RequestBody Plane stewardess) {
        flightPlannerService.savePlane(stewardess);
    }

    @PostMapping("/createMost")
    public void addMost(@RequestBody MostVisitedPlaces stewardess) {
        flightPlannerService.saveMost(stewardess);
    }

    @GetMapping("/api/pilots")
    public List<AirLinePilot> allPilots() {
        return flightPlannerService.getAllPilots();
    }

    @GetMapping("/api/planes")
    public List<Plane> allPlanes() {
        return flightPlannerService.getAllPlanes();
    }

    @GetMapping("/api/stewardesses")
    public List<Stewardess> allStewardesses() {
        return flightPlannerService.getAllStewardesses();
    }

    @GetMapping("/api/getPilotById")
    public AirLinePilot getPilotById(@RequestParam String id) {
        return flightPlannerService.getPilotById(id);
    }

    @GetMapping("/api/getPlaneById")
    public Plane getPlaneById(@RequestParam String id) {
        return flightPlannerService.getPlaneById(id);
    }

    @GetMapping("/api/getStewardessById")
    public Stewardess getStewardessById(@RequestParam String id) {
        return flightPlannerService.getStewardessById(id);
    }

}
