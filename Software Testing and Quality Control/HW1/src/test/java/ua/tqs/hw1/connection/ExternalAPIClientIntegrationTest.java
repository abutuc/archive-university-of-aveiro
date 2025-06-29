package ua.tqs.hw1.connection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.tqs.hw1.data.Cache;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

class ExternalAPIClientIntegrationTest {

    private ExternalAPIClient client;

    @BeforeEach
    void setUp(){
        Cache cache = new Cache();
        client = new ExternalAPIClient(cache);
    }

    @Test
    void whenDoGetAveiroCoordinates_thenReturnCorrectString() throws IOException, ParseException {
        String apiResponse = client.doHttpGet("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        JSONArray objArray = (JSONArray) new JSONParser().parse(apiResponse);
        JSONObject obj = (JSONObject) objArray.get(0);
        String name = (String) obj.get("name");
        String country = (String) obj.get("country");
        Double lon = (Double) obj.get("lon");
        Double lat = (Double) obj.get("lat");

        assertThat(name).isEqualTo("Aveiro");
        assertThat(country).isEqualTo("PT");
        assertThat(lon).isEqualTo(-8.6537841);
        assertThat(lat).isEqualTo(40.640496);
    }

    @Test
    void whenDoGetRuaDomingues_thenReturnEmptyArray() throws IOException, ParseException {
        String apiResponse = client.doHttpGet("http://api.openweathermap.org/geo/1.0/direct?q=RuaDomingues&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        JSONArray objArray = (JSONArray) new JSONParser().parse(apiResponse);
        assertThat(objArray).isEmpty();
    }

    @Test
    void whenDoGetSameURLTwice_thenFetchFromCache() throws IOException, ParseException {
        String apiResponse = client.doHttpGet("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        JSONArray objArray = (JSONArray) new JSONParser().parse(apiResponse);
        JSONObject obj = (JSONObject) objArray.get(0);
        String name = (String) obj.get("name");
        String country = (String) obj.get("country");
        Double lon = (Double) obj.get("lon");
        Double lat = (Double) obj.get("lat");

        assertThat(name).isEqualTo("Aveiro");
        assertThat(country).isEqualTo("PT");
        assertThat(lon).isEqualTo(-8.6537841);
        assertThat(lat).isEqualTo(40.640496);

        apiResponse = client.doHttpGet("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        objArray = (JSONArray) new JSONParser().parse(apiResponse);
        obj = (JSONObject) objArray.get(0);
        name = (String) obj.get("name");
        country = (String) obj.get("country");
        lon = (Double) obj.get("lon");
        lat = (Double) obj.get("lat");

        assertThat(name).isEqualTo("Aveiro");
        assertThat(country).isEqualTo("PT");
        assertThat(lon).isEqualTo(-8.6537841);
        assertThat(lat).isEqualTo(40.640496);
    }



    @Test
    void whenDoGetValidGeocoding_thenReturnCorrectObject() throws IOException, ParseException, URISyntaxException {
        JSONObject geocoding = client.getGeocoding("Aveiro");
        String name = (String) geocoding.get("name");
        String country = (String) geocoding.get("country");
        Double lon = (Double) geocoding.get("lon");
        Double lat = (Double) geocoding.get("lat");
        assertThat(name).isEqualTo("Aveiro");
        assertThat(country).isEqualTo("PT");
        assertThat(lon).isEqualTo(-8.6537841);
        assertThat(lat).isEqualTo(40.640496);
    }

    @Test
    void whenDoGetInvalidGeocoding_thenReturnNullObject() throws URISyntaxException, ParseException, IOException {
        JSONObject geocoding = client.getGeocoding("Rua Domingues");
        assertThat(geocoding).isEmpty();
    }

    @Test
    void whenDoGetAirPollutionNow_thenReturnJsonObject() throws URISyntaxException, ParseException, IOException {
        assertThat(client.getAirPollutionNow(40.640496, -8.6537841)).isInstanceOf(JSONObject.class);
    }

    @Test
    void whenDoGetAirPollutionForecast_thenReturnJsonArray() throws URISyntaxException, ParseException, IOException {
        assertThat(client.getAirPollutionForecast(40.640496, -8.6537841)).isInstanceOf(JSONArray.class);

    }

}
