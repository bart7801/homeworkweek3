package com.bart.homeworkweek3.controller;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.Color;
import com.bart.homeworkweek3.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@Validated @PathVariable long id) {
        Optional<Car> car = carService.findCarById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@Validated @PathVariable String color) {
        try {
            List<Car> carList = carService.findCarByColor(Color.valueOf(color.toUpperCase()));
            if (!carList.isEmpty()) {
                return new ResponseEntity<>(carList, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity addNewCar(@Validated @RequestBody Car car) {
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping()
    public ResponseEntity modifyCar(@Validated @RequestBody Car car) {
        Optional<Car> modifyCar = carService.modifyCar(car);
        if (modifyCar.isPresent()) {
            return new ResponseEntity<>(modifyCar.get().getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping()
    public ResponseEntity modifyColorCarById(@Validated @RequestBody Car car) {
        boolean result = carService.modifyColorCarById(car);
        if (result) {
            return new ResponseEntity(car.getColor(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@Validated @PathVariable long id) {
        Optional<Car> deleteCar = carService.deleteCarById(id);
        if (deleteCar.isPresent()) {
            return new ResponseEntity(deleteCar.get().getId(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
