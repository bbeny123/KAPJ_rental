package pl.beny.rental.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CARS")
@SequenceGenerator(name = "SEQ_CAR", allocationSize = 1)
@NamedEntityGraph(name = Car.EntityGraphs.WITH_RESERVATIONS, attributeNodes = @NamedAttributeNode("reservations"))
public class Car {

    public interface EntityGraphs {
        String WITH_RESERVATIONS = "Car.WITH_RESERVATIONS";
    }

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAR")
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

    @Column(name = "CAR_AVAILABLE", nullable = false)
    private boolean available = true;

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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
