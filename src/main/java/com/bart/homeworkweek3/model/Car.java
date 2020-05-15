package com.bart.homeworkweek3.model;

import org.springframework.hateoas.ResourceSupport;

public class Car extends ResourceSupport {

    private long carId;
    private String mark;
    private String model;
    private Color color;

    public Car() {
    }

    public Car(long carId, String mark, String model, Color color) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}