package pl.beny.rental.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import pl.beny.rental.model.Reservation;

import java.io.ByteArrayOutputStream;
import java.time.temporal.ChronoUnit;

public class PDFUtil {

    public static ByteArrayOutputStream getReservationInvoice(Reservation rsv) throws Exception {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, result);

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.open();
        document.add(new Paragraph("Rental - THE BEST RENTAL IN THE UNIVERSE", FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK)));
        document.add(Chunk.NEWLINE);
        document.add(new Chunk(String.format("Client: %s\n", rsv.getUser().getFullName()), font));
        document.add(new Chunk(String.format("Car: %s, %s\n", rsv.getCar().getName(), rsv.getCar().getPlate()), font));
        document.add(new Chunk(String.format("Reservation start: %tF\n",  rsv.getDateStart()), font));
        document.add(new Chunk(String.format("Reservation end: %tF\n", rsv.getDateEnd()), font));
        document.add(new Chunk(String.format("Cost: %d EUR\n", (ChronoUnit.DAYS.between(rsv.getDateStart(), rsv.getDateEnd()) + 1) * 100), font));
        document.close();

        return result;
    }

}
