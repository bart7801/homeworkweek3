package com.bart.homeworkweek3.controller;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.Color;
import com.bart.homeworkweek3.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //pobranie calej listy
    @GetMapping()
    public ResponseEntity<Resources<Car>> getAllCars() {
        List<Car> carList = carService.getAllCars();
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
        Link link = linkTo(CarController.class).withSelfRel();
        Resources<Car> carResources = new Resources<>(carList, link);
        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    //pobranie po id
    @GetMapping("/{id}")
    public ResponseEntity<Resource<Car>> getCarById(@PathVariable long id) {
        Link link = linkTo(CarController.class).slash(id).withSelfRel();
        Optional<Car> carById = carService.getCarById(id);
        Resource<Car> carResource = new Resource<>(carById.get(), link);
        return new ResponseEntity<>(carResource, HttpStatus.OK);
    }

    //pobranie po kolorze
    @GetMapping("/color/{color}")
    public ResponseEntity<Resources<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> carList = carService.getCarByColor(Color.valueOf(color.toUpperCase()));
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getId()).withSelfRel()));
        carList.forEach(car -> car.add(linkTo(CarController.class).withRel("allColors")));
        Link link = linkTo(CarController.class).withSelfRel();
        Resources<Car> carResources = new Resources<>(carList, link);
        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    //dodawanie elementu
    @PostMapping()
    public ResponseEntity addNewCar(@Validated @RequestBody Car car) {
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //modyfikowanie, nadpisywanie pozycji
    @PutMapping()
    public ResponseEntity modifyCar(@Validated @RequestBody Car car) {
        Optional<Car> modifyCar = carService.modifyCar(car);
        if (modifyCar.isPresent()) {
            return new ResponseEntity<>(modifyCar.get().getCarId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //modyfikowanie, nadpisywanie jednego elementu z pol pozycji
    @PatchMapping()
    public ResponseEntity modifyColorCarById(@Validated @RequestBody Car car) {
        boolean result = carService.modifyColorCarById(car);
        if (result) {
            return new ResponseEntity(car.getColor(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //usuwanie elementu
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@Validated @PathVariable long id) {
        Optional<Car> deleteCar = carService.deleteCarById(id);
        if (deleteCar.isPresent()) {
            return new ResponseEntity(deleteCar.get().getCarId(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
