package pl.beny.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.beny.rental.model.Car;

public class CarResponse {

    private Long id;
    private String make;
    private String model;
    private String colour;
    private String plate;
    private boolean available;
    private boolean rentable;

    public CarResponse(Car car) {
        this.id = car.getId();
        this.make = car.getMake();
        this.model = car.getModel();
        this.colour = car.getColour();
        this.plate = car.getPlate();
        this.available = car.isAvailable();
        this.rentable = car.isAvailable() && car.getReservations().stream().noneMatch(rsv -> rsv.getDateEnd() == null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @JsonIgnore
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isRentable() {
        return rentable;
    }

    public void setRentable(boolean rentable) {
        this.rentable = rentable;
    }
}
