package airport.psk.demo.models.others;

import airport.psk.demo.Config.Consts;
import airport.psk.demo.utils.ToJSONConverter;
import lombok.Data;
import org.json.JSONObject;

@Data
public class Weather {

    private String cityName;
    private String description;
    private double temperature;
    private double perceptibleTemperature;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private double humidity;
    private double visibility;
    private double windSpeed;
    private double cloudy;

    public Weather(String cityName, String description, double temperature, double perceptibleTemperature, double minTemperature, double maxTemperature,
                   double pressure, double humidity, double visibility, double windSpeed, double cloudy) {
        this.cityName = cityName;
        this.description = description;
        this.temperature = temperature;
        this.perceptibleTemperature = perceptibleTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.cloudy = cloudy;
    }

    public static Weather getWeatherFromJSON(String city) {
        JSONObject jsonWithWeather = ToJSONConverter.getJSONFromURL(Consts.getApiWeatherURL(city));

        String nameJSON = jsonWithWeather.get("name").toString();
        String visibilityJSON = jsonWithWeather.get("visibility").toString();
        JSONObject main = new JSONObject(jsonWithWeather.get("main").toString());
        JSONObject clouds = new JSONObject(jsonWithWeather.get("clouds").toString());
        JSONObject wind = new JSONObject(jsonWithWeather.get("wind").toString());
        int weaLength = jsonWithWeather.get("weather").toString().length();
        JSONObject wea = new JSONObject(jsonWithWeather.get("weather").toString().substring(1, weaLength - 1));

        String cityName = nameJSON;
        String description = wea.get("description").toString();
        double temperature = Double.parseDouble(main.get("temp").toString());
        double perceptibleTemperature = Double.parseDouble(main.get("feels_like").toString());
        double minTemperature = Double.parseDouble(main.get("temp_min").toString());
        double maxTemperature = Double.parseDouble(main.get("temp_max").toString());
        double pressure = Double.parseDouble(main.get("pressure").toString());
        double humidity = Double.parseDouble(main.get("humidity").toString());
        double visibility = Double.parseDouble(visibilityJSON);
        double windSpeed = Double.parseDouble(wind.get("speed").toString());
        double cloudy = Double.parseDouble(clouds.get("all").toString());

        return new Weather(cityName, description, temperature, perceptibleTemperature, minTemperature,
                maxTemperature, pressure, humidity, visibility, windSpeed, cloudy);
    }
}
