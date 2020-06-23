package airport.psk.demo.models.flightThings;

import airport.psk.demo.Config.Consts;
import airport.psk.demo.utils.ToJSONConverter;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

@Data
public class AirPort {

    private String city;
    private String code;
    private String countryCode;
    private String name;
    private double latitude;
    private double longitude;

    public AirPort(String city, String code, String countryCode, String name, double latitude, double longitude) {
        this.city = city;
        this.code = code;
        this.countryCode = countryCode;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static List<AirPort> getAirPortsFromJSON(String cityName ){
        JSONArray jsonWithAirPorts = ToJSONConverter.
                getJSONArrayWithAirPorts(Consts.getApiAirPortsURL(cityName));

        List<AirPort> airPorts = new LinkedList<>();
        for (Object object : jsonWithAirPorts) {
            JSONObject airPortJSON = new JSONObject(object.toString());

            String city = airPortJSON.get("city").toString();
            String code = airPortJSON.get("code").toString();
            String countryCode = airPortJSON.get("countryCode").toString();
            String name = airPortJSON.get("name").toString();
            JSONObject location = new JSONObject(airPortJSON.get("location").toString());
            double latitude = Double.parseDouble(location.get("latitude").toString());
            double longitude = Double.parseDouble(location.get("longitude").toString());

            airPorts.add(new AirPort(city,code,countryCode,name,latitude,longitude));
        }
        return airPorts;
    }

    public String getCity() {
        return city;
    }
}
