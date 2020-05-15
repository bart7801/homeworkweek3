package com.bart.homeworkweek3.service;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "MASERATI", "GRAN TURISMO", Color.RED));
        carList.add(new Car(2L, "FERRARI", "CALIFORNIA", Color.BLACK));
        carList.add(new Car(3L, "BUGATTI", "CHIRON", Color.WHITE));
    }

    @Override
    public List<Car> getAllCars() {

        return carList;
    }

    @Override
    public Optional<Car> findCarById(long id) {

        return carList
                .stream()
                .filter(car -> car.getId() == id)
                .findFirst();
    }

    @Override
    public List<Car> findCarByColor(Color color) {
        return carList
                .stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        return carList.add(car);
    }

    @Override
    public Optional<Car> modifyCar(Car car) {
        Optional<Car> modifyCar = carList
                .stream()
                .filter(carId -> carId.getId() == car.getId())
                .findFirst();
        if (modifyCar.isPresent()) {
            carList.remove(modifyCar.get());
            carList.add(car);
        }
        return modifyCar;
    }

    @Override
    public boolean modifyColorCarById(Car newCar) {
        Optional<Car> car = carList
                .stream()
                .filter(Car -> Car.getId() == newCar.getId())
                .findFirst();
        if (car.isPresent()) {
            if (newCar.getMark() != null) {
                car.get().setColor(newCar.getColor());
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<Car> deleteCarById(long id) {
        Optional<Car> removeCar = carList
                .stream()
                .filter(carId -> carId.getId() == id)
                .findFirst();
        if (removeCar.isPresent()) {
            carList.remove(removeCar.get());
        }
        return removeCar;
    }
}