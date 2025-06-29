package ua.tqs.lab3_2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.lab3_2.boundary.CarController;
import ua.tqs.lab3_2.data.Car;
import ua.tqs.lab3_2.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car a4Audi = new Car("Audi", "A4 Avant");

        when(service.save(Mockito.any())).thenReturn(a4Audi);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(a4Audi)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("A4 Avant")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car a4Avant = new Car("Audi", "A4 Avant");
        Car a1Sportback = new Car("Audi", "A1 Sportback");
        Car tRoc = new Car("Volkswagen", "T-Roc");
        Car golf = new Car("Volkswagen", "Golf");

        List<Car> allCars = Arrays.asList(a4Avant, a1Sportback, tRoc, golf);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].model", is(a4Avant.getModel())))
                .andExpect(jsonPath("$[1].model", is(a1Sportback.getModel())))
                .andExpect(jsonPath("$[2].model", is(tRoc.getModel())))
                .andExpect(jsonPath("$[3].model", is(golf.getModel())));

        verify(service, times(1)).getAllCars();

    }

    @Test
    void givenCarID1_thenReturnA1SportBack() throws Exception {
        Car a1Sportback = new Car("Audi", "A1 Sportback");

        when(service.getCarDetails(any())).thenReturn(Optional.of(a1Sportback));

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId", is(a1Sportback.getCarId())))
                .andExpect(jsonPath("$.model", is(a1Sportback.getModel())));

        verify(service, times(1)).getCarDetails(1L);
    }
}