package ua.tqs.lab3_2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ua.tqs.lab3_2.data.Car;
import ua.tqs.lab3_2.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindGolfByID_thenReturnGolfCar(){
        Car golf = new Car("Volkswagen", "Golf");
        entityManager.persistAndFlush(golf);

        Car found = carRepository.findByCarId(golf.getCarId());
        assertThat(found).isEqualTo(golf);
    }

    @Test
    void whenInvalidCarID_thenReturnNull(){
        Car fromDb = carRepository.findByCarId(-1L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars(){
        Car golf = new Car("Volkswagen", "Golf");
        Car a4Avant = new Car("Audi", "A4 Avant");
        Car a1Sportback = new Car("Audi", "A1 Sportback");

        entityManager.persist(golf);
        entityManager.persist(a4Avant);
        entityManager.persist(a1Sportback);

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getCarId).containsOnly(golf.getCarId(),
                a4Avant.getCarId(), a1Sportback.getCarId());

    }
}