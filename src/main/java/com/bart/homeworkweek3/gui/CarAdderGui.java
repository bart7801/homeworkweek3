package com.bart.homeworkweek3.gui;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.CarType;
import com.bart.homeworkweek3.model.Color;
import com.bart.homeworkweek3.model.Fuel;
import com.bart.homeworkweek3.service.CarService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("add-car")
@StyleSheet("/css/style2.css")
public class CarAdderGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarAdderGui(CarService carService) {
        this.carService = carService;

        TextField textFieldBrand = new TextField("BRAND");
        textFieldBrand.setWidth("200px");
        TextField textFieldModel = new TextField("MODEL");
        textFieldModel.setWidth("200px");
        NumberField numberFieldYearProduction = new NumberField("YEAR PRODUCTION");
        numberFieldYearProduction.setWidth("200px");
        numberFieldYearProduction.setMin(1995);
        numberFieldYearProduction.setMax(2020);
        numberFieldYearProduction.setErrorMessage("Set year of production from 1995 to 2020.");
        numberFieldYearProduction.setHasControls(true);
        ComboBox<Fuel> comboBoxFuel = new ComboBox<>("FUEL", Fuel.values());
        comboBoxFuel.setWidth("200px");
        ComboBox<CarType> comboBoxCarType = new ComboBox<>("CAR TYPE", CarType.values());
        comboBoxCarType.setWidth("200px");
        ComboBox<Color> comboBoxColor = new ComboBox<>("COLOR", Color.values());
        comboBoxColor.setWidth("200px");

        NativeButton confirmButton = new NativeButton("Add Car", event -> {
            carService.addCar(new Car(
                    textFieldBrand.getValue(),
                    textFieldModel.getValue(),
                    numberFieldYearProduction.getValue(),
                    comboBoxFuel.getValue(),
                    comboBoxCarType.getValue(),
                    comboBoxColor.getValue()));
            getUI().ifPresent(ui ->
                    ui.navigate("main-view"));

            Label content = new Label(
                    "You added new car to the list");
            NativeButton buttonInside = new NativeButton("Super");
            Notification notification = new Notification(content, buttonInside);
            notification.setDuration(5000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.open();

        });
        add(textFieldBrand, textFieldModel, numberFieldYearProduction, comboBoxFuel, comboBoxCarType, comboBoxColor, confirmButton);
    }
}
