package ua.tqs.lab7_4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.tqs.lab7_4.data.Car;
import ua.tqs.lab7_4.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarRestControllerTemplateIT {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withUsername("tqs")
            .withPassword("tqs")
            .withDatabaseName("tqs");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb(){
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar_thenStatus201(){
        Car golf = new Car("Volkswagen", "Golf");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", golf, Car.class);
        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getCarId).containsOnly(1L);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void givenCars_whenGetEmployees_thenStatus200(){
        createTestCar("Volkswagen", "Golf");
        createTestCar("Audi", "A1 Sportback");

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Golf", "A1 Sportback");
    }

    @Test
    void givenValidCarID_returnCar_thenStatus200(){
        Long carID = createTestCar("Audi", "A1 Sportback");

        ResponseEntity<Car> response = restTemplate
                .exchange("/api/cars/" + carID, HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getCarId).isEqualTo(carID);
    }

    @Test
    void givenInvalidCarID_returnNull_thenStatus404(){
        ResponseEntity<Car> response = restTemplate
                .exchange("/api/cars" + -1L, HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).extracting(Car::getCarId).isNull();
    }

    private Long createTestCar(String maker, String model){
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
        return car.getCarId();
    }


}
