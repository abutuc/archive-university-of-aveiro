package ua.tqs.hw1.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.tqs.hw1.connection.ExternalAPIClient;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.data.Coordinates;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirQualityServiceUnitTest {

    @Mock
    private ExternalAPIClient client;

    @InjectMocks
    AirQualityService service;

    @Test
    void whenSearchAveiro_thenReturnAveiroAirQuality() throws IOException, URISyntaxException, ParseException {
        Coordinates aveiroCoords = new Coordinates(40.640496, -8.6537841);
        HashMap<String, Double> components = new HashMap<>();
        String[] components_text = {"co", "no", "no2", "o3", "so2", "pm2_5", "pm10", "nh3"};
        Double[] components_value = {223.64, 0.0, 3.34, 85.83, 2.0, 2.09, 3.69, 1.95};
        for (int i = 0; i < components_text.length; i++){
            components.put(components_text[i], components_value[i]);
        }
        Date date = new Date(1680399166L * 1000L);
        AirQuality aveiroAirQuality = new AirQuality("Aveiro, PT", aveiroCoords, 2, components, date.toString());
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse("{\"local_names\":{\"ar\":\"آويرو\",\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"lt\":\"Aveiras\",\"hu\":\"Aveiro\"},\"country\":\"PT\",\"name\":\"Aveiro\",\"lon\":-8.6537841,\"lat\":40.640496}");
        when(client.getGeocoding("Aveiro"))
                .thenReturn(json);

        json = (JSONObject) parser.parse("{\"main\":{\"aqi\":2},\"components\":{\"co\":223.64,\"no\":0,\"no2\":3.34,\"o3\":85.83,\"so2\":2,\"pm2_5\":2.09,\"pm10\":3.69,\"nh3\":1.95},\"dt\":1680399166}");
        when(client.getAirPollutionNow(40.640496, -8.6537841 ))
                .thenReturn(json);

        assertThat(service.getLocationAirQualityNow("Aveiro")).isEqualTo(new ResponseEntity<>(aveiroAirQuality, HttpStatus.OK));

        Mockito.verify(client, Mockito.times(1)).getGeocoding("Aveiro");
        Mockito.verify(client, Mockito.times(1)).getAirPollutionNow(40.640496, -8.6537841);
    }

    @Test
    void whenSearchRuaDomingues_thenReturnNoContent() throws IOException, URISyntaxException, ParseException {
        when(client.getGeocoding("Rua Domingues"))
                .thenReturn(new JSONObject());

        assertThat(service.getLocationAirQualityNow("Rua Domingues")).isEqualTo(ResponseEntity.noContent().build());

        Mockito.verify(client, Mockito.times(1)).getGeocoding("Rua Domingues");
    }


    @Test
    void whenSearchAveiro_thenReturnAveiroAirQualityForecast() throws URISyntaxException, ParseException, IOException {
        Coordinates aveiroCoords = new Coordinates(40.640496, -8.6537841);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse("{\"local_names\":{\"ar\":\"آويرو\",\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"lt\":\"Aveiras\",\"hu\":\"Aveiro\"},\"country\":\"PT\",\"name\":\"Aveiro\",\"lon\":-8.6537841,\"lat\":40.640496}");
        when(client.getGeocoding("Aveiro"))
                .thenReturn(json);


        JSONArray jsonArray = (JSONArray) parser.parse("[{\n" +
                "            \"main\": {\n" +
                "                \"aqi\": 2\n" +
                "            },\n" +
                "            \"components\": {\n" +
                "                \"co\": 367.17,\n" +
                "                \"no\": 0,\n" +
                "                \"no2\": 19.71,\n" +
                "                \"o3\": 47.21,\n" +
                "                \"so2\": 1.46,\n" +
                "                \"pm2_5\": 16.65,\n" +
                "                \"pm10\": 20.7,\n" +
                "                \"nh3\": 3.29\n" +
                "            },\n" +
                "            \"dt\": 1680822000\n" +
                "        },\n" +
                "        {\n" +
                "            \"main\": {\n" +
                "                \"aqi\": 2\n" +
                "            },\n" +
                "            \"components\": {\n" +
                "                \"co\": 393.87,\n" +
                "                \"no\": 0,\n" +
                "                \"no2\": 25.71,\n" +
                "                \"o3\": 25.03,\n" +
                "                \"so2\": 1.03,\n" +
                "                \"pm2_5\": 16.78,\n" +
                "                \"pm10\": 21.02,\n" +
                "                \"nh3\": 4.88\n" +
                "            },\n" +
                "            \"dt\": 1681088400\n" +
                "        }]");

        when(client.getAirPollutionForecast(40.640496, -8.6537841)).thenReturn(jsonArray);

        Map<String, Double> components_1 = new HashMap<>();
        String[] components_text_1 = {"co", "no", "no2", "o3", "so2", "pm2_5", "pm10", "nh3"};
        Double[] components_value_1 = {367.17, 0.0, 19.71, 47.21, 1.46, 16.65, 20.7, 3.29};
        for (int i = 0; i < components_text_1.length; i++){
            components_1.put(components_text_1[i], components_value_1[i]);
        }

        Date date1 = new Date(1680822000L * 1000L);

        AirQuality airQuality1 = new AirQuality("Aveiro, PT", aveiroCoords, 2, components_1, date1.toString());

        Map<String, Double> components_2 = new HashMap<>();
        String[] components_text_2 = {"co", "no", "no2", "o3", "so2", "pm2_5", "pm10", "nh3"};
        Double[] components_value_2 = {393.87, 0.0, 25.71, 25.03, 1.03, 16.78, 21.02, 4.88};
        for (int i = 0; i < components_text_2.length; i++){
            components_2.put(components_text_2[i], components_value_2[i]);
        }

        Date date2 = new Date(1681088400L * 1000L);

        AirQuality airQuality2 = new AirQuality("Aveiro, PT", aveiroCoords, 2, components_2, date2.toString());
        List<AirQuality> list = new ArrayList<>();
        list.add(airQuality1);
        list.add(airQuality2);

        assertThat(service.getLocationAirQualityForecast("Aveiro")).isEqualTo(new ResponseEntity<>(list, HttpStatus.OK));

        Mockito.verify(client, Mockito.times(1)).getGeocoding("Aveiro");
        Mockito.verify(client, Mockito.times(1)).getAirPollutionForecast(40.640496, -8.6537841);

    }

    @Test
    void whenSearchRuaDomingues_thenReturnNoForecastContent() throws IOException, URISyntaxException, ParseException {
        when(client.getGeocoding("Rua Domingues"))
                .thenReturn(new JSONObject());

        assertThat(service.getLocationAirQualityForecast("Rua Domingues")).isEqualTo(ResponseEntity.noContent().build());

        Mockito.verify(client, Mockito.times(1)).getGeocoding("Rua Domingues");
    }

}