package ua.tqs.hw1.boundary;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.tqs.hw1.data.AirQuality;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CacheStatsControllerIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    void setUp(){
        restTemplate.exchange("/air_quality/now?location=Aveiro", HttpMethod.GET,
                null, new ParameterizedTypeReference<AirQuality>() {
                });
        System.out.println("exchanged");
    }


    @Test
    @Order(1)
    void whenGetCacheMisses_thenReturnCacheMisses(){
        ResponseEntity<Integer> response = restTemplate.exchange("/cache/stats/misses", HttpMethod.GET,
                null, new ParameterizedTypeReference<Integer>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(Integer.class);
        assertThat(response.getBody()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void whenGetCacheHits_thenReturnCacheHits(){
        ResponseEntity<Integer> response = restTemplate.exchange("/cache/stats/hits", HttpMethod.GET,
                null, new ParameterizedTypeReference<Integer>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(Integer.class);
        assertThat(response.getBody()).isEqualTo(2);
    }


    @Test
    @Order(3)
    void whenGetCacheRequests_thenReturnCacheRequests(){
        ResponseEntity<Integer> response = restTemplate.exchange("/cache/stats/requests", HttpMethod.GET,
                null, new ParameterizedTypeReference<Integer>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(Integer.class);
        assertThat(response.getBody()).isEqualTo(6);
    }
}
