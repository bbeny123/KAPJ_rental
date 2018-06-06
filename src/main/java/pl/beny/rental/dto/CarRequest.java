package pl.beny.rental.dto;

import pl.beny.rental.model.Car;

public class CarRequest {

    private String make;
    private String model;
    private String colour;
    private String plate;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Car getCar() {
        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setColour(colour);
        car.setPlate(plate);
        return car;
    }

}
