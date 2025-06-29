package ua.tqs.hw1.service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.tqs.hw1.connection.IExternalAPIClient;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.data.Coordinates;
import ua.tqs.hw1.utils.AirQualityComparator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class AirQualityService {

    private final IExternalAPIClient client;

    @Autowired
    public AirQualityService(IExternalAPIClient client){
        this.client = client;
    }

    public ResponseEntity<AirQuality> getLocationAirQualityNow(String location) throws URISyntaxException, IOException, ParseException {
        JSONObject geocoding = client.getGeocoding(location);
        if (geocoding.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        String name = (String) geocoding.get("name");
        String country = (String) geocoding.get("country");
        Double lon = (Double) geocoding.get("lon");
        Double lat = (Double) geocoding.get("lat");

        JSONObject now = client.getAirPollutionNow(lat, lon);

        AirQuality airquality = getAirQuality(name, country, lat, lon, now);

        return new ResponseEntity<>(airquality, HttpStatus.OK);

    }

    public ResponseEntity<List<AirQuality>> getLocationAirQualityForecast(String location) throws URISyntaxException, IOException, ParseException {
        JSONObject geocoding = client.getGeocoding(location);
        if (geocoding.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        String name = (String) geocoding.get("name");
        String country = (String) geocoding.get("country");
        Double lon = (Double) geocoding.get("lon");
        Double lat = (Double) geocoding.get("lat");

        JSONArray forecast = client.getAirPollutionForecast(lat, lon);
        HashMap<String, AirQuality> map = new HashMap<>();

        for (Object o : forecast) {
            JSONObject obj = (JSONObject) o;
            AirQuality airQuality = getAirQuality(name, country, lat, lon, obj);
            String[] day = airQuality.getDate().split(" ");
            String key = day[0] + day[1] + day[2];
            if (map.containsKey(key)) {
                continue;
            }
            map.put(key, airQuality);
        }

        List<AirQuality> list = new ArrayList<>(map.values());
        list.sort(new AirQualityComparator());
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    private AirQuality getAirQuality(String name, String country, Double lat, Double lon, JSONObject obj ){
        Integer qualityIndex = ((Long)((JSONObject) obj.get("main")).get("aqi")).intValue();
        String date = new Date(Long.parseLong(obj.get("dt").toString())*1000L).toString();
        Set<Map.Entry<String, Long>> components = ((JSONObject)obj.get("components")).entrySet();
        HashMap<String, Double> map = new HashMap<>();
        for(Map.Entry<String, Long> entry : components)
        {
            map.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
        }
        return new AirQuality(name + ", " + country, new Coordinates(lat, lon), qualityIndex, map, date);
    }
}
