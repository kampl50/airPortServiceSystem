package airport.psk.demo.utils;

import airport.psk.demo.models.flightThings.AirPort;

import java.util.LinkedList;
import java.util.List;

public class Distance {
    private static final double WARSAW_LATITUDE = 52.16;
    private static final double WARSAW_LONGITUDE = 20.96;
    private static final double RADIUS = 6378.16;
    private static final double PIx = 3.141592653589793;

    public static double getDistance(String city) {
        List<Double> listWithParams = getLatAndLong(city);
        double latitude = listWithParams.get(0);
        double longitude = listWithParams.get(1);

        return calculateDistance(latitude, longitude);
    }

    private static double calculateDistance(double latitude, double longitude) {
        double degreeLongitude = radians(longitude - WARSAW_LONGITUDE);
        double degreeLatitude = radians(latitude - WARSAW_LATITUDE);

        double a = (Math.sin(degreeLatitude / 2) * Math.sin(degreeLatitude / 2)) + Math.cos(radians(WARSAW_LATITUDE)) * Math.cos(radians(latitude)) * (Math.sin(degreeLongitude / 2) * Math.sin(degreeLongitude / 2));
        double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return angle * RADIUS;
    }

    private static double radians(double x) {
        return x * PIx / 180;
    }

    private static List<Double> getLatAndLong(String city) {
//
//        JSONArray jsonWithAirPorts = ToJSONConverter.
//                getJSONArrayWithAirPorts(Consts.getApiAirPortsURL(city));
//        List<Double> params = new LinkedList<>();
//        for (Object object : jsonWithAirPorts) {
//            JSONObject airPortJSON = new JSONObject(object.toString());
//
//
//            JSONObject location = new JSONObject(airPortJSON.get("location").toString());
//            double latitude = Double.parseDouble(location.get("latitude").toString());
//            double longitude = Double.parseDouble(location.get("longitude").toString());
//
//            params.add(latitude);
//            params.add(longitude);
//        }
//        return params;
        List<Double> params = new LinkedList<>();
        List<AirPort> airPorts = AirPortsUtlis.get(city);

        for (AirPort airPort : airPorts) {
            double latitude = airPort.getLatitude();
            double longitude = airPort.getLongitude();

            params.add(latitude);
            params.add(longitude);
        }
        return params;
    }
}
