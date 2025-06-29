package ua.tqs.lab7_2;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.lab7_2.boundary.CarController;
import ua.tqs.lab7_2.data.Car;
import ua.tqs.lab7_2.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;


@WebMvcTest(CarController.class)
class CarControllerRestAssuredTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(mvc);
    }
    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car a4Audi = new Car("Audi", "A4 Avant");

        Mockito.when(service.save(Mockito.any())).thenReturn(a4Audi);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(JsonUtils.toJson(a4Audi))
                .when()
                .post("/api/cars")
                .then()
                .statusCode(201)
                .body("model", is("A4 Avant"));

        Mockito.verify(service, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray(){
        Car a4Avant = new Car("Audi", "A4 Avant");
        Car a1Sportback = new Car("Audi", "A1 Sportback");
        Car tRoc = new Car("Volkswagen", "T-Roc");
        Car golf = new Car("Volkswagen", "Golf");

        List<Car> allCars = Arrays.asList(a4Avant, a1Sportback, tRoc, golf);

        Mockito.when( service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given().when().get("/api/cars").then().statusCode(200)
                .body("$", hasSize(4))
                .body("model", hasItems(a4Avant.getModel(), a1Sportback.getModel(), tRoc.getModel(), golf.getModel()));

        Mockito.verify(service, Mockito.times(1)).getAllCars();

    }

    @Test
    void givenCarID1_thenReturnA1SportBack(){
        Car a1Sportback = new Car("Audi", "A1 Sportback");

        Mockito.when(service.getCarDetails(Mockito.any())).thenReturn(Optional.of(a1Sportback));

        RestAssuredMockMvc.given().when().get("/api/cars/1").then().statusCode(200)
                .body("carId", is(a1Sportback.getCarId()))
                .body("model", is(a1Sportback.getModel()));


        Mockito.verify(service, Mockito.times(1)).getCarDetails(1L);
    }
}
