package ua.tqs.hw1.connection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IExternalAPIClient {

    String doHttpGet(String url) throws IOException;
    JSONObject getGeocoding(String location) throws URISyntaxException, ParseException, IOException;

    JSONObject getAirPollutionNow(Double lat, Double lon) throws URISyntaxException, ParseException, IOException;

    JSONArray getAirPollutionForecast(Double lat, Double lon) throws URISyntaxException, ParseException, IOException;

}