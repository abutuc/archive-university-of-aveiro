package ua.tqs.hw1.boundary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.tqs.hw1.data.AirQuality;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirQualityControllerIntegrationTest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void clearCacheStats(){
        restTemplate.exchange("/cache/stats/clear", HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
        });
    }

    @Test
    void givenLocationAveiro_thenReturnAveiroAirQualityNow(){
        ResponseEntity<AirQuality> response = restTemplate.exchange("/air_quality/now?location=Aveiro", HttpMethod.GET,
                null, new ParameterizedTypeReference<AirQuality>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(AirQuality.class);
        AirQuality airQuality = response.getBody();
        assertThat(airQuality).isNotNull();
        assertThat(airQuality.getLocation()).isEqualTo("Aveiro, PT");
    }

    @Test
    void whenSearchInvalid_thenReturnEmptyResponseEntityNow() {
        ResponseEntity<AirQuality> response = restTemplate.exchange("/air_quality/now?location=RuaDomingues", HttpMethod.GET,
                null, new ParameterizedTypeReference<AirQuality>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void whenSearchAveiro_thenReturnResponseEntityAveiroAirQualityForecast() {
        ResponseEntity<List<AirQuality>> response = restTemplate.exchange("/air_quality/forecast?location=Aveiro", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<AirQuality>>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(List.class);
        List<AirQuality> airQualityForecast = response.getBody();
        assertThat(airQualityForecast).isNotNull();
        assertThat(airQualityForecast.size()).isEqualTo(5);
        assertThat(airQualityForecast.get(0).getLocation()).isEqualTo("Aveiro, PT");
    }

    @Test
    void whenSearchInvalid_thenReturnEmptyResponseEntityForecast() {
        ResponseEntity<List<AirQuality>> response = restTemplate.exchange("/air_quality/forecast?location=RuaDomingues", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<AirQuality>>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

}
