package pl.beny.rental.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RSV_ID")
    private Long id;

    @Column(name = "RSV_USR_ID", nullable = false)
    private Long usrId;

    @Column(name = "RSV_CAR_ID", nullable = false)
    private Long carId;

    @Column(name = "RSV_DATE_START", nullable = false)
    private LocalDate dateStart;

    @Column(name = "RSV_DATE_END", nullable = false)
    private LocalDate dateEnd;

    @Column(name = "RSV_APPROVED", nullable = false)
    private boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
