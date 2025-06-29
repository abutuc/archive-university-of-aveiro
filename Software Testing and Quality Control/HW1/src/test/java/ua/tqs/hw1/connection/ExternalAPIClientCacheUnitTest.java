package ua.tqs.hw1.connection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.hw1.data.Cache;

import java.io.IOException;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExternalAPIClientCacheUnitTest {

    @Mock
    private Cache cache;

    @InjectMocks
    private ExternalAPIClient client;


    @Test
    void whenDoGetAveiroCoordinates_thenReturnCorrectString() throws IOException, ParseException {
        Mockito.when(cache.isQueryInCache(Mockito.anyString())).thenReturn(false);
        Mockito.doNothing().when(cache).setEntry(Mockito.anyString(), Mockito.anyString());
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
        Mockito.verify(cache, Mockito.times(1)).isQueryInCache(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(1)).setEntry(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void whenDoGetRuaDomingues_thenReturnEmptyArray() throws IOException, ParseException {
        Mockito.when(cache.isQueryInCache(Mockito.anyString())).thenReturn(false);
        Mockito.doNothing().when(cache).setEntry(Mockito.anyString(), Mockito.anyString());
        String apiResponse = client.doHttpGet("http://api.openweathermap.org/geo/1.0/direct?q=RuaDomingues&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        JSONArray objArray = (JSONArray) new JSONParser().parse(apiResponse);
        assertThat(objArray).isEmpty();
        Mockito.verify(cache, Mockito.times(1)).isQueryInCache(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(1)).setEntry(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void whenDoGetSameURLTwice_thenFetchFromCache() throws IOException, ParseException {
        Mockito.when(cache.isQueryInCache("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7")).thenReturn(true);
        Mockito.when(cache.getValue("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7")).thenReturn("[{\"name\":\"Aveiro\",\"local_names\":{\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\",\"lt\":\"Aveiras\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]");
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
        Mockito.verify(cache, Mockito.times(1)).isQueryInCache("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
        Mockito.verify(cache, Mockito.times(1)).getValue("http://api.openweathermap.org/geo/1.0/direct?q=Aveiro&limit=1&appid=8b5f3b52ca7814b00fa0587ca39d6ab7");
    }



    @Test
    void whenDoGetValidGeocoding_thenReturnCorrectObject() throws IOException, ParseException, URISyntaxException {
        Mockito.when(cache.isQueryInCache(Mockito.anyString())).thenReturn(false);
        Mockito.doNothing().when(cache).setEntry(Mockito.anyString(), Mockito.anyString());
        JSONObject geocoding = client.getGeocoding("Aveiro");
        String name = (String) geocoding.get("name");
        String country = (String) geocoding.get("country");
        Double lon = (Double) geocoding.get("lon");
        Double lat = (Double) geocoding.get("lat");
        assertThat(name).isEqualTo("Aveiro");
        assertThat(country).isEqualTo("PT");
        assertThat(lon).isEqualTo(-8.6537841);
        assertThat(lat).isEqualTo(40.640496);
        Mockito.verify(cache, Mockito.times(1)).isQueryInCache(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(1)).setEntry(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void whenDoGetInvalidGeocoding_thenReturnNullObject() throws URISyntaxException, ParseException, IOException {
        Mockito.when(cache.isQueryInCache(Mockito.anyString())).thenReturn(false);
        Mockito.doNothing().when(cache).setEntry(Mockito.anyString(), Mockito.anyString());
        JSONObject geocoding = client.getGeocoding("Rua Domingues");
        assertThat(geocoding).isEmpty();
        Mockito.verify(cache, Mockito.times(1)).isQueryInCache(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(1)).setEntry(Mockito.anyString(), Mockito.anyString());
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