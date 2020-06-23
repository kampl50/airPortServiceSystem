package airport.psk.demo.utils;

import airport.psk.demo.enums.FlightClass;
import airport.psk.demo.models.dtoModels.EmployerDTO;
import airport.psk.demo.models.dtoModels.FlightDTO;
import airport.psk.demo.models.flightThings.Flight;
import airport.psk.demo.models.flightThings.MostVisitedPlaces;
import airport.psk.demo.models.flightThings.Plane;
import airport.psk.demo.models.others.Ticket;
import airport.psk.demo.repositories.FlightRepository;
import airport.psk.demo.repositories.MostVisitedPlacesRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoUtils {

    private static final String MONGO_DB_HOST_NAME = "localhost";
    private static final String MONGO_DATABASE_NAME = "test";
    private static final int MONGO_DB_PORT = 27017;

    private static final MongoClient client = new MongoClient(MONGO_DB_HOST_NAME, MONGO_DB_PORT);
    private static final MongoDatabase dataBase = client.getDatabase(MONGO_DATABASE_NAME);
//
//    private static final MongoClientURI uri = new MongoClientURI(
//            "mongodb+srv://kamil:kamil@cluster0-dckyn.mongodb.net/<dbname>?retryWrites=true&w=majority");
//
//    private static final  MongoClient mongoClient = new MongoClient(uri);
//    private static final  MongoDatabase dataBase = mongoClient.getDatabase("airport");

    public static void incrementPopularity(String city, MostVisitedPlacesRepository repository) {

        MongoCollection<Document> collection = dataBase.getCollection("mostVisitedPlaces");
        Document query = new Document();
        query.append("cityName", city);
        Document setData = new Document();
        setData.append("popularity", getPopularity(city, repository.findAll()));
        Document update = new Document();
        update.append("$set", setData);

        collection.updateOne(query, update);
    }

    private static int getPopularity(String city, List<MostVisitedPlaces> list) {
        for (MostVisitedPlaces mostVisitedPlaces : list) {
            if (mostVisitedPlaces.getCityName().equals(city)) {
                return mostVisitedPlaces.getPopularity() + 1;
            }
        }
        return 0;
    }

    public static void updateAvailability(FlightDTO flightDTO) {
        List<EmployerDTO> airLinePilot = flightDTO.getCrew().getAirLinePilots();
        List<EmployerDTO> stewardesses = flightDTO.getCrew().getStewardesses();
        Plane plane = flightDTO.getPlane();
        setAvailability(airLinePilot, stewardesses, plane);
    }

    private static void setAvailability(List<EmployerDTO> pilots, List<EmployerDTO> stewardesses, Plane plane) {
        MongoCollection<Document> pilotsCollection = dataBase.getCollection("airLinePilot");
        MongoCollection<Document> stewardessCollection = dataBase.getCollection("stewardess");
        MongoCollection<Document> planesCollection = dataBase.getCollection("plane");
        Document query = new Document();

        for (EmployerDTO employerDTO : pilots) {
            ObjectId objectId = new ObjectId(employerDTO.getId());
            query.append("_id", objectId);
            Document setData = new Document();
            setData.append("availability", "UNAVAILABLE");
            Document update = new Document();
            update.append("$set", setData);
            pilotsCollection.updateOne(query, update);
        }

        for (EmployerDTO employerDTO : stewardesses) {
            ObjectId objectId = new ObjectId(employerDTO.getId());
            query.append("_id", objectId);
            Document setData = new Document();
            setData.append("availability", "UNAVAILABLE");
            Document update = new Document();
            update.append("$set", setData);
            stewardessCollection.updateOne(query, update);
        }

        ObjectId objectId = new ObjectId(plane.getId());
        query.append("_id", objectId);
        Document setData = new Document();
        setData.append("availability", "UNAVAILABLE");
        Document update = new Document();
        update.append("$set", setData);
        planesCollection.updateOne(query, update);
    }

    public static void updateSeats(Ticket ticket, FlightRepository flightRepository) {
        Flight flight = flightRepository.findById(ticket.getFlightID()).get();
        FlightClass flightClass = ticket.getFlightClass();

        MongoCollection<Document> flightsCollection = dataBase.getCollection("flight");
        Document query = new Document();
        ObjectId objectId = new ObjectId(flight.getId());
        query.append("_id", objectId);
        Document setData = new Document();
        setData.append("numberOfFreePlacesAll", flight.getNumberOfFreePlacesAll() - 1);
        switch (flightClass) {
            case ECONOMY:
                setData.append("numberOfFreePlacesInEconomyClass", flight.getNumberOfFreePlacesInEconomyClass() - 1);
                break;
            case BUSINESS:
                setData.append("numberOfFreePlacesInBusinessClass", flight.getNumberOfFreePlacesInBusinessClass() - 1);
                break;
            case FIRST_CLASS:
                setData.append("numberOfFreePlacesInFirstClass", flight.getNumberOfFreePlacesInFirstClass() - 1);
                break;
        }
        Document update = new Document();
        update.append("$set", setData);
        flightsCollection.updateOne(query, update);

    }

}

