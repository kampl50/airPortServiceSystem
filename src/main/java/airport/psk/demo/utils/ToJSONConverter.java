package airport.psk.demo.utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ToJSONConverter {

    public static JSONObject getJSONFromURL(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response =
                null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new JSONObject(response.body());
    }

    public static JSONArray getJSONArrayWithAirPorts(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("x-rapidapi-host", "cometari-airportsfinder-v1.p.rapidapi.com")
                .setHeader("x-rapidapi-key",  "dca4a19b59mshfeefe42ea502c49p160ad8jsn53441421fc6d")
                .build();

        HttpResponse<String> response =
                null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response.body());
        } catch (JSONException exception) {
            System.out.println("Repairing json...");
            jsonArray = new JSONArray(repairJSONFormat(response.body()));
        }
        return jsonArray;
    }

    private static String repairJSONFormat(String json) {
        if (json.startsWith("["))
            return json.substring(1, json.length() - 1);
        else
            return json;
    }
}
