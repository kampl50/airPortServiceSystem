package airport.psk.demo.Config;

public class Consts {
    private static final String API_WEATHER_DOMAIN = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_WEATHER_USER_KEY = "&appid=9d843d0218a4772f88088a9e8dbc0283&units=metric&fbclid=IwAR0E6Gj7Vxavlz553BCi48GtbYDrxBHnW1QMTF6GQsJPnmB_9jfQimIDt54";
    private static final String API_AIR_PORTS_DOMAIN = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-text?text=";


    public static String getApiWeatherURL(String cityName) {
        return API_WEATHER_DOMAIN + cityName + API_WEATHER_USER_KEY;
    }

    public static String getApiAirPortsURL(String cityName) {
        return API_AIR_PORTS_DOMAIN + cityName;
    }
}
