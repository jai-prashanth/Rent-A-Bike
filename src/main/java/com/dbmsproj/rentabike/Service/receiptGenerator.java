package com.dbmsproj.rentabike.Service;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Models.bookings;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import com.dbmsproj.rentabike.Repository.bookingsRepository;
import com.dbmsproj.rentabike.security.SecurityServices;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class receiptGenerator {
    @Autowired
    SecurityServices securityServices;
    @Autowired
    bookingsRepository bookingsRepo;
    @Autowired
    bikesRepository bikesRepo;

//    private bookingsRepository(bookingsRepository bookingRepo){
//        this.bookingRepo=bookingRepo;
//    }
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        User user=securityServices.findLoggedInUser();
        List<bookings> booking=bookingsRepo.findByUserId(user.getUserId());



        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA, 14);

        Paragraph paragraph1 = new Paragraph("\t\t\t\t\t\t\t\t\t\t\t\tRentABike Bike Rental Services", fontTitle);
        Paragraph paragraph2 = new Paragraph("Address: Bidar, Karnataka, India", fontSubtitle);

        paragraph1.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

        Image logo = Image.getInstance("src/main/resources/static/images/RentABike_logo.png");
        logo.setAlignment(Image.ALIGN_LEFT);
        logo.scaleAbsolute(100,100);

        Table table = new Table(2);
        table.setWidths(new int[]{120, 500});
        table.addCell(createImageCell("src/main/resources/static/images/RentABike_logo.png"));
        table.addCell(paragraph1);


        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA, 12);

        String newLine = "_";
        for(int i=0; i<77; i++) {
            newLine += "_";
        }
        Paragraph blankLine = new Paragraph(newLine, fontParagraph);
        Paragraph paragraph3 = new Paragraph("Booking Details cum Receipt", fontParagraph);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

        Table details = new Table(2);

        details.setWidths(new int[]{200, 200});
        details.addCell("\tBooking ID");
        details.addCell("\t "+String.valueOf(booking.get(0).getBookingId()));
        details.addCell("\tCustomer Name");
        details.addCell("\t "+user.getUserFirstName()+" "+user.getUserLastName());
        details.addCell("\tCustomer Phone");
        details.addCell("\t "+user.getPhone());
        details.addCell("\tBike Registration Number");
        details.addCell("\t "+booking.get(0).getRegistrationNumber());
//        details.addCell("\tBike Model");
//        details.addCell("\tBike Model Should come here");  // no need
        details.addCell("\tBike Pickup time");
        details.addCell("\t "+(booking.get(0).getPickupTime()));
        details.addCell("\tBike Drop time");
        details.addCell("\t "+(booking.get(0).getReturnTime()));
//        details.addCell("\tBike Rent per hour");
//        details.addCell("\tBike Rent per hour Should come here"); // no need
//        details.addCell("\tHours");
//        details.addCell("\tHours Should come here"); //no need
//        details.addCell("\tTotal Rent");
//        details.addCell("\tTotal Rent Should come here"); // no need
        details.addCell("\tTotal Payment");
        details.addCell("\t "+(booking.get(0).getTotalPayment()));
        details.addCell("\tBooking Time");
        details.addCell("\t "+(booking.get(0).getBookingTime()));



        Paragraph thanks = new Paragraph("Thank you for choosing RentABike Bike Rental Services.\nWe hope you had a great experience with us.\nPlease visit us again.", fontParagraph);
        thanks.setAlignment(Paragraph.ALIGN_CENTER);
        // Add things here
        document.add(table);
        document.add(paragraph2);
        document.add(blankLine);
        document.add(paragraph3);
        document.add(blankLine);
        document.add(details);
        document.add(blankLine);

        document.add(thanks);
        document.close();

    }

    public static Cell createImageCell(String imgPath) throws IOException {
        Image img = Image.getInstance(imgPath);
        img.scaleAbsolute(100,100);
        Cell cell = new Cell();
        cell.setUseBorderPadding(true);
        cell.add(img);
        cell.setBorderWidth(0);
        return cell;
    }

//    public static Cell createTextCell(String text) throws IOException {
//        Cell cell = new Cell();
//        Paragraph p = new Paragraph(text);
//        p.setAlignment(Paragraph.ALIGN_CENTER);
//        cell.add(p);
//        cell.setBorderWidth(0);
//        return cell;
//    }
}
