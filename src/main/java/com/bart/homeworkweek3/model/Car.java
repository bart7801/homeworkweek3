package com.bart.homeworkweek3.model;

import com.vaadin.flow.component.polymertemplate.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "CAR ID CAN'T BE NULL")
    private Long carId;
    @NotNull
    @Size(min = 1)
    private String brand;
    @NotNull
    @Size(min = 1)
    private String model;
    private Double yearProduction;
    private Fuel fuel;
    private CarType carType;
    private Color color;

    public Car() {
    }

    public Car(String brand, String model, Double yearProduction, Fuel fuel, CarType carType, Color color) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.yearProduction = yearProduction;
        this.fuel = fuel;
        this.carType = carType;
        this.color = color;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(Double yearProduction) {
        this.yearProduction = yearProduction;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", mark='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearProduction=" + yearProduction +
                ", fuel=" + fuel +
                ", carType=" + carType +
                ", color=" + color +
                '}';
    }

}
