package pl.beny.rental.service;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Reservation;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.ReservationRepository;
import pl.beny.rental.util.PDFUtil;
import pl.beny.rental.util.RentalException;

import java.time.LocalDate;

@Service
public class ReservationService extends BaseService<Reservation> {

    private ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void changeStatus(UserContext ctx, Long rsvId, String status) throws RentalException {
        Reservation rsv = findOneEmployee(ctx, rsvId);
        rsv.setDateEnd(LocalDate.now());
        rsv.setStatus(status.equals(Reservation.Actions.CANCEL) ? Reservation.Status.CANCELED : Reservation.Status.FINISHED);
        saveEmployee(ctx, rsv);
    }

    public ByteOutputStream getInvoice(UserContext ctx, Long rsvId) throws Exception {
        Reservation rsv = findOne(rsvId);
        if (!ctx.isAdmin() && !ctx.isEmployee() && !rsv.getUser().getId().equals(ctx.getUser().getId())) {
            throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
        }
        if (!Reservation.Status.FINISHED.equals(rsv.getStatus())) {
            throw new RentalException(RentalException.RentalErrors.INVOICE_NOT_AVAILABLE);
        }
        return PDFUtil.getReservationInvoice(rsv);
    }

}
