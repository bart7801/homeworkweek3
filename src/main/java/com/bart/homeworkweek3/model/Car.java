package com.bart.homeworkweek3.model;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car extends ResourceSupport {

    @NotNull(message = "CAR ID CAN'T BE NULL")
    private long carId;
    @NotNull
    @Size(min = 1)
    private String mark;
    @NotNull
    @Size(min = 1)
    private String model;
    @Min(1900)
    @Max(2020)
    private int year;
    private Color color;

    public Car() {
    }

    public Car(long carId, String mark, String model, int year, Color color) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public long getCarId() {
        return carId;
    }

    public void setId(long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}