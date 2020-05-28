package com.bart.homeworkweek3.gui;

import com.bart.homeworkweek3.model.Car;
import com.bart.homeworkweek3.model.CarType;
import com.bart.homeworkweek3.model.Color;
import com.bart.homeworkweek3.model.Fuel;
import com.bart.homeworkweek3.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route("main-view")
@StyleSheet("/css/style1.css")
public class CarsShowGui extends VerticalLayout {

    private CarService carService;
    private ListDataProvider<Car> dataProvider;
    private Grid<Car> grid;

    @Autowired
    public CarsShowGui(CarService carService) {
        dataProvider = new ListDataProvider<>(carService.getCarList());
        grid = new Grid<>();
        this.carService = carService;
        getCars();
    }

    private void getCars() {

        grid.setDataProvider(dataProvider);

        Grid.Column<Car> brandColumn = grid.addColumn(Car::getBrand).setHeader("BRAND");
        Grid.Column<Car> modelColumn = grid.addColumn(Car::getModel).setHeader("MODEL");
        Grid.Column<Car> yearProductionColumn = grid.addColumn(Car::getYearProduction).setHeader("YEAR PRODUCTION");
        Grid.Column<Car> fuelColumn = grid.addColumn(Car::getFuel).setHeader("FUEL");
        Grid.Column<Car> carTypeColumn = grid.addColumn(Car::getCarType).setHeader("CAR TYPE");
        Grid.Column<Car> colorColumn = grid.addColumn(Car::getColor).setHeader("COLOR");


        HeaderRow filterRow = grid.appendHeaderRow();

        createSearchBar(brandColumn, modelColumn, yearProductionColumn, fuelColumn, carTypeColumn, colorColumn, filterRow);

        grid.addComponentColumn(this::createRemoveButton);
        grid.addComponentColumn(this::createEditButton);

        grid.appendFooterRow().getCell(brandColumn).setComponent(createAddButton());
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        add(grid);

    }

    private void createSearchBar(Grid.Column<Car> brandColumn,
                                 Grid.Column<Car> modelColumn,
                                 Grid.Column<Car> yearProductionColumn,
                                 Grid.Column<Car> fuelColumn,
                                 Grid.Column<Car> carTypeColumn,
                                 Grid.Column<Car> colorColumn,
                                 HeaderRow filterRow) {

        //brand
        TextField brandField = new TextField();
        brandField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getBrand(),
                        brandField.getValue())));
        brandField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(brandColumn).setComponent(brandField);
        brandField.setSizeFull();
        brandField.setPlaceholder("Search by BRAND");

        //model
        TextField modelField = new TextField();
        modelField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getModel(),
                        modelField.getValue())));
        modelField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(modelColumn).setComponent(modelField);
        modelField.setSizeFull();
        modelField.setPlaceholder("Search by MODEL");

        //year production
        TextField yearProductionField = new TextField();
        yearProductionField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(String.valueOf(car.getYearProduction()),
                        yearProductionField.getValue())));
        yearProductionField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(yearProductionColumn).setComponent(yearProductionField);
        yearProductionField.setSizeFull();
        yearProductionField.setPlaceholder("Search by YEAR PRODUCTION");

        //fuel
        TextField fuelField = new TextField();
        fuelField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(String.valueOf(car.getFuel()),
                        fuelField.getValue())));
        fuelField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(fuelColumn).setComponent(fuelField);
        fuelField.setSizeFull();
        fuelField.setPlaceholder("Search by FUEL");

        //car type
        TextField carTypeField = new TextField();
        carTypeField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(String.valueOf(car.getCarType()),
                        carTypeField.getValue())));
        carTypeField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(carTypeColumn).setComponent(carTypeField);
        carTypeField.setSizeFull();
        carTypeField.setPlaceholder("Search by CAR TYPE");

        //color
        TextField colorField = new TextField();
        colorField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(String.valueOf(car.getColor()),
                        colorField.getValue())));
        colorField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(colorColumn).setComponent(colorField);
        colorField.setSizeFull();
        colorField.setPlaceholder("Search by COLOR");
    }

    private Button createEditButton(Car car) {
        VerticalLayout verticalLayout = new VerticalLayout();

        return new Button("Edit", clickEvent -> {
            Dialog dialog = new Dialog();
            Input brand = new Input();
            brand.setValue(car.getBrand());
            Input model = new Input();
            model.setValue(car.getModel());
            Input yearProduction = new Input();
            yearProduction.setValue(String.valueOf(car.getYearProduction()));
            Input fuel = new Input();
            fuel.setValue(String.valueOf(car.getFuel()));
            Input carType = new Input();
            carType.setValue(String.valueOf(car.getCarType()));
            Input color = new Input();
            color.setValue(String.valueOf(car.getColor()));


            NativeButton confirmButton = new NativeButton("Confirm", event -> {
                car.setBrand(brand.getValue());
                car.setModel(model.getValue());
                car.setYearProduction(Double.valueOf(yearProduction.getValue()));
                car.setFuel(Fuel.valueOf(fuel.getValue()));
                car.setCarType(CarType.valueOf(carType.getValue()));
                car.setColor(Color.valueOf(color.getValue()));
                dialog.close();
                dataProvider.refreshAll();
            });
            verticalLayout.add(brand, model, yearProduction, fuel, carType, color, confirmButton);
            dialog.add(verticalLayout);
            dialog.open();

        });
    }

    private Button createRemoveButton(Car item) {
        return new Button("Remove", clickEvent -> {
            dataProvider.getItems().remove(item);
            dataProvider.refreshAll();
        });
    }

    private Button createAddButton() {
        Button button = new Button("Add New Car");
        button.addClickListener(e ->
                button.getUI().ifPresent(ui ->
                        ui.navigate("add-car")));
        return button;
    }
}
