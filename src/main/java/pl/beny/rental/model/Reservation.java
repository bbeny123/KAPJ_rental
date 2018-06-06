package pl.beny.rental.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
@SequenceGenerator(name = "SEQ_RSV")
public class Reservation {

    public interface Actions {
        String APPROVE = "approve";
        String CANCEL = "cancel";
        String FINISH = "finish";
    }

    public enum Status {
        WAITING("WAITING"),
        ACTIVE("ACTIVE"),
        CANCELED("CANCELED"),
        FINISHED("FINISHED");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    @ManyToOne
    @JoinColumn(name="RSV_USR_ID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="RSV_CAR_ID", nullable=false)
    private Car car;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RSV")
    @Column(name = "RSV_ID")
    private Long id;

    @Column(name = "RSV_DATE_START", nullable = false)
    private LocalDate dateStart;

    @Column(name = "RSV_DATE_END")
    private LocalDate dateEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "RSV_STATUS", length = 10, nullable = false)
    private Status status = Status.WAITING;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
