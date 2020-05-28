package com.bart.homeworkweek3.service;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.CarType;
import com.bart.homeworkweek3.model.Color;
import com.bart.homeworkweek3.model.Fuel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car("TESLA", "MODEL S", 2020., Fuel.ELECTRIC, CarType.HATCHBACK, Color.BLACK));
        carList.add(new Car("FERRARI", "CALIFORNIA", 2018., Fuel.PETROL, CarType.COUPE, Color.RED));
        carList.add(new Car("BUGATTI", "VEYRON", 2019., Fuel.PETROL, CarType.SEDAN, Color.WHITE));
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
