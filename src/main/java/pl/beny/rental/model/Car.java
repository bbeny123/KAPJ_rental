package pl.beny.rental.model;

import javax.persistence.*;

@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private Long id;

    @Column(name = "CAR_MAKE", length = 60, nullable = false)
    private String make;

    @Column(name = "CAR_MODEL", length = 60, nullable = false)
    private String model;

    @Column(name = "CAR_COLOUR", length = 30, nullable = false)
    private String colour;

    @Column(name = "CAR_PLATE", length = 10, nullable = false, unique = true)
    private String plate;

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
}
