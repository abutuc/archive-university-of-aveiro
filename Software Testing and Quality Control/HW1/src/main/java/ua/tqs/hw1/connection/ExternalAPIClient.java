package ua.tqs.hw1.connection;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.tqs.hw1.data.Cache;
import ua.tqs.hw1.utils.ConfigUtils;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class ExternalAPIClient implements IExternalAPIClient {
    private final String apiKey = ConfigUtils.getPropertyFromConfig("key");
    private static final String API_KEY_PARAM = "appid";
    private final Cache cache;


    @Autowired
    public ExternalAPIClient(Cache cache){
        this.cache = cache;
    }

    @Override
    public String doHttpGet(String url) throws IOException {
        if (cache.isQueryInCache(url))
            return cache.getValue(url);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            String value = EntityUtils.toString(entity);
            cache.setEntry(url, value);
            return value;
        }
    }

    @Override
    public JSONObject getGeocoding(String location) throws URISyntaxException, ParseException, IOException {
        URIBuilder uriBuilder = new URIBuilder("http://api.openweathermap.org/geo/1.0/direct");
        uriBuilder.addParameter("q", location);
        uriBuilder.addParameter("limit", "1");
        uriBuilder.addParameter(API_KEY_PARAM, this.apiKey);
        String apiResponse = this.doHttpGet(uriBuilder.build().toString());
        JSONArray objArray = (JSONArray) new JSONParser().parse(apiResponse);
        if (objArray.isEmpty()){
            return new JSONObject();
        }
        return (JSONObject) objArray.get(0);
    }

    @Override
    public JSONObject getAirPollutionNow(Double lat, Double lon) throws URISyntaxException, ParseException, IOException {
        URIBuilder uriBuilder = new URIBuilder("http://api.openweathermap.org/data/2.5/air_pollution");
        uriBuilder.addParameter("lat", lat.toString());
        uriBuilder.addParameter("lon", lon.toString());
        uriBuilder.addParameter(API_KEY_PARAM, apiKey);
        String apiResponse = this.doHttpGet(uriBuilder.build().toString());
        JSONObject obj = (JSONObject) new JSONParser().parse(apiResponse);
        return (JSONObject)((JSONArray) obj.get("list")).get(0);
    }

    @Override
    public JSONArray getAirPollutionForecast(Double lat, Double lon) throws URISyntaxException, ParseException, IOException {
        URIBuilder uriBuilder = new URIBuilder("http://api.openweathermap.org/data/2.5/air_pollution/forecast");
        uriBuilder.addParameter("lat", lat.toString());
        uriBuilder.addParameter("lon", lon.toString());
        uriBuilder.addParameter(API_KEY_PARAM, apiKey);
        String apiResponse = this.doHttpGet(uriBuilder.build().toString());
        JSONObject obj = (JSONObject) new JSONParser().parse(apiResponse);
        return (JSONArray) obj.get("list");
    }

}
