package com.bart.homeworkweek3.service;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Optional<Car> getCarById(long id);

    List<Car> getCarByColor(Color color);

    boolean addCar(Car car);

    Optional<Car> modifyCar(Car car);

    boolean modifyColorCarById(Car car);

    Optional<Car> deleteCarById(long id);

}
