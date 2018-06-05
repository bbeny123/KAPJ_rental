package pl.beny.rental.service;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
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
        changeStatus(ctx, rsvId, Reservation.Actions.CANCEL);
    }

    public void changeStatus(UserContext ctx, Long rsvId, String status) throws RentalException {
        Reservation rsv = findOne(rsvId);
        checkAuthorization(ctx, rsv);
        rsv.setDateEnd(LocalDate.now());
        rsv.setStatus(status.equals(Reservation.Actions.CANCEL) ? Reservation.Status.CANCELED : Reservation.Status.FINISHED);
        saveEmployee(ctx, rsv);
    }

    public void prepareInvoiceResponse(UserContext ctx, Long rsvId, HttpServletResponse response) throws Exception {
        Reservation rsv = findOne(rsvId);
        checkAuthorization(ctx, rsv);
        if (!Reservation.Status.FINISHED.equals(rsv.getStatus())) {
            throw new RentalException(RentalException.RentalErrors.INVOICE_NOT_AVAILABLE);
        }

        ByteOutputStream invoice = PDFUtil.getReservationInvoice(rsv);
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
