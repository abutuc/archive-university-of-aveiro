package ua.tqs.lab3_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.lab3_2.data.Car;
import ua.tqs.lab3_2.data.CarRepository;
import ua.tqs.lab3_2.service.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    private Car a4Avant;
    private Car a1Sportback;
    private Car tRoc;

    private Car golf;

    @BeforeEach
    void setUp() {
        a4Avant = new Car("Audi", "A4 Avant");
        a4Avant.setCarId(1L);
        a1Sportback = new Car("Audi", "A1 Sportback");
        a1Sportback.setCarId(2L);
        tRoc = new Car("Volkswagen", "T-Roc");
        tRoc.setCarId(3L);

        List<Car> allCars = Arrays.asList(a4Avant, a1Sportback, tRoc);

        Mockito.when(carRepository.findByCarId(a4Avant.getCarId())).thenReturn(a4Avant);
        Mockito.when(carRepository.findByCarId(a1Sportback.getCarId())).thenReturn(a1Sportback);
        Mockito.when(carRepository.findByCarId(tRoc.getCarId())).thenReturn(tRoc);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);

        golf = new Car("Volkswagen", "Golf");
        Mockito.when(carRepository.save(any())).thenReturn(golf);

    }

    @AfterEach
    void tearDown(){
        a4Avant = a1Sportback = tRoc = null;
    }

    @Test
    void whenSearchValidId_thenCarShouldBeFound(){
        Long id = 1L;
        Car found = carService.getCarDetails(1L).orElse(new Car());
        assertThat(found.getCarId()).isEqualTo(id);
        Mockito.verify(carRepository, Mockito.times(1)).findByCarId(1L);
    }

    @Test
    void whenSearchInvalidId_thenCarShouldNotBeFound(){
        Car fromDb = carService.getCarDetails(-1L).orElse(null);
        assertThat(fromDb).isNull();
        Mockito.verify(carRepository, Mockito.times(1)).findByCarId(-1L);
    }


    @Test
    void given3Cars_whenGetAll_thenReturn3Records(){
        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).hasSize(3).contains(a4Avant, a1Sportback, tRoc);
        Mockito.verify(carRepository, Mockito.times(1)).findAll();
    }


    @Test
    void whenCreateGolf_thenReturnGolf(){
        Car created = carService.save(golf);
        assertThat(created).isEqualTo(golf);
        Mockito.verify(carRepository, Mockito.times(1)).save(golf);
    }
}