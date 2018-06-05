package pl.beny.rental.dto;

import pl.beny.rental.model.Reservation;

import java.time.LocalDate;

public class ReservationResponse {

    private Long id;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String status;
    private String user;
    private String car;
    private String plate;
    private String action;
    private boolean invoiceAvailable;

    public ReservationResponse(Reservation rsv) {
        this.id = rsv.getId();
        this.dateStart = rsv.getDateStart();
        this.dateEnd = rsv.getDateEnd();
        this.status = rsv.getStatus().getStatus();
        this.user = rsv.getUser().getIdFullName();
        this.car = rsv.getCar().getName();
        this.plate = rsv.getCar().getPlate();
        this.action = Reservation.Status.ACTIVE.equals(rsv.getStatus()) ? Reservation.Actions.FINISH : Reservation.Status.WAITING.equals(rsv.getStatus()) ? Reservation.Actions.CANCEL : null;
        this.invoiceAvailable = Reservation.Status.FINISHED.equals(rsv.getStatus());
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isInvoiceAvailable() {
        return invoiceAvailable;
    }

    public void setInvoiceAvailable(boolean invoiceAvailable) {
        this.invoiceAvailable = invoiceAvailable;
    }
}
