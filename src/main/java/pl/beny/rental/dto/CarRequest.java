package pl.beny.rental.dto;

import org.hibernate.validator.constraints.Length;
import pl.beny.rental.model.Car;

import javax.validation.constraints.NotEmpty;

public class CarRequest {

    private String make;
    private String model;
    private String colour;
    private String plate;

    @NotEmpty
    @Length(max = 60)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotEmpty
    @Length(max = 60)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotEmpty
    @Length(max = 30)
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @NotEmpty
    @Length(max = 10)
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
