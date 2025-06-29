package ua.tqs.lab7_2.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.lab7_2.data.Car;
import ua.tqs.lab7_2.data.CarDTO;
import ua.tqs.lab7_2.service.CarManagerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody CarDTO car){
        return new ResponseEntity<>(carService.save(car.toCarEntity()), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> car = carService.getCarDetails(id);
        return car.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
