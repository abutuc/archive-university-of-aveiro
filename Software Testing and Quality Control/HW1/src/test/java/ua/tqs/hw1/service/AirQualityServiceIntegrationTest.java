package ua.tqs.hw1.service;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.tqs.hw1.connection.ExternalAPIClient;
import ua.tqs.hw1.connection.IExternalAPIClient;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.data.Cache;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AirQualityServiceIntegrationTest {
    private AirQualityService service;
    @BeforeEach
    void setUp(){
        Cache cache = new Cache();
        IExternalAPIClient client = new ExternalAPIClient(cache);
        service = new AirQualityService(client);
    }


    @Test
    void whenSearchAveiro_thenReturnResponseEntityAveiroAirQualityNow() throws IOException, URISyntaxException, ParseException {
        assertThat(service.getLocationAirQualityNow("Aveiro")).isInstanceOf(ResponseEntity.class);
        ResponseEntity<AirQuality> airQualityResponseEntity = service.getLocationAirQualityNow("Aveiro");
        assertThat(airQualityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(airQualityResponseEntity.getBody()).isInstanceOf(AirQuality.class);
        AirQuality airQuality = airQualityResponseEntity.getBody();
        assertThat(airQuality).isNotNull();
        assertThat(airQuality.getLocation()).isEqualTo("Aveiro, PT");
    }

    @Test
    void whenSearchInvalid_thenReturnEmptyResponseEntityNow() throws URISyntaxException, IOException, ParseException {
        assertThat(service.getLocationAirQualityNow("Rua Domingues")).isInstanceOf(ResponseEntity.class);
        ResponseEntity<AirQuality> airQualityResponseEntity = service.getLocationAirQualityNow("Rua Domingues");
        assertThat(airQualityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(airQualityResponseEntity.getBody()).isNull();
    }

    @Test
    void whenSearchAveiro_thenReturnResponseEntityAveiroAirQualityForecast() throws IOException, URISyntaxException, ParseException {
        assertThat(service.getLocationAirQualityForecast("Aveiro")).isInstanceOf(ResponseEntity.class);
        ResponseEntity<List<AirQuality>> airQualityForecastResponseEntity = service.getLocationAirQualityForecast("Aveiro");
        assertThat(airQualityForecastResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(airQualityForecastResponseEntity.getBody()).isInstanceOf(List.class);
        List<AirQuality> airQualityForecast = airQualityForecastResponseEntity.getBody();
        assertThat(airQualityForecast).isNotNull();
        assertThat(airQualityForecast.size()).isEqualTo(5);
        assertThat(airQualityForecast.get(0).getLocation()).isEqualTo("Aveiro, PT");
    }

    @Test
    void whenSearchInvalid_thenReturnEmptyResponseEntityForecast() throws URISyntaxException, IOException, ParseException {
        assertThat(service.getLocationAirQualityForecast("Rua Domingues")).isInstanceOf(ResponseEntity.class);
        ResponseEntity<List<AirQuality>> airQualityResponseEntity = service.getLocationAirQualityForecast("Rua Domingues");
        assertThat(airQualityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(airQualityResponseEntity.getBody()).isNull();
    }

}
