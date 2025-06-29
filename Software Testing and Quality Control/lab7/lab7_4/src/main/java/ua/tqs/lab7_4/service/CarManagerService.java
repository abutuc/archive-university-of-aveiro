package ua.tqs.lab7_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tqs.lab7_4.data.Car;
import ua.tqs.lab7_4.data.CarRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        Car car = carRepository.findByCarId(id);
        return car == null ? Optional.empty() : Optional.of(car);
    }

}
