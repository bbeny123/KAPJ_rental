package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Reservation;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.ReservationRepository;
import pl.beny.rental.util.PDFUtil;
import pl.beny.rental.util.RentalException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService extends BaseService<Reservation> {

    private ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Reservation> findAllByUserId(UserContext ctx) {
        return repository.findAllByUserId(ctx.getUser().getId());
    }

    public void cancel(UserContext ctx, Long rsvId) throws RentalException {
        Reservation rsv = findOne(rsvId);
        checkAuthorization(ctx, rsv);
        rsv.setDateEnd(LocalDate.now());
        rsv.setStatus(Reservation.Status.CANCELED);
        save(rsv);
    }

    public void changeStatus(UserContext ctx, Long rsvId, String status) throws RentalException {
        if (Reservation.Actions.CANCEL.equals(status)) {
            cancel(ctx, rsvId);
        } else {
            approveOrFinish(ctx, rsvId, status);
        }
    }

    private void approveOrFinish(UserContext ctx, Long rsvId, String status) throws RentalException {
        Reservation rsv = findOneEmployee(ctx, rsvId);
        if (Reservation.Actions.APPROVE.equals(status)) {
            rsv.setDateStart(LocalDate.now());
            rsv.setStatus(Reservation.Status.ACTIVE);
        } else if (Reservation.Actions.FINISH.equals(status)) {
            rsv.setDateEnd(LocalDate.now());
            rsv.setStatus(Reservation.Status.FINISHED);
        }
        saveEmployee(ctx, rsv);
    }

    public void prepareInvoiceResponse(UserContext ctx, Long rsvId, HttpServletResponse response) throws Exception {
        Reservation rsv = findOne(rsvId);
        checkAuthorization(ctx, rsv);
        if (!Reservation.Status.FINISHED.equals(rsv.getStatus())) {
            throw new RentalException(RentalException.RentalErrors.INVOICE_NOT_AVAILABLE);
        }

        ByteArrayOutputStream invoice = PDFUtil.getReservationInvoice(rsv);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"invoice" + rsv.getId() + ".pdf\"");
        response.setContentLength(invoice.size());
        invoice.writeTo(response.getOutputStream());
    }

    private void checkAuthorization(UserContext ctx, Reservation rsv) throws RentalException {
        if (!ctx.isAdmin() && !ctx.isEmployee() && !rsv.getUser().getId().equals(ctx.getUser().getId())) {
            throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
        }
    }

}
