package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Service.receiptGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class receiptGeneratorController {

    private final receiptGenerator receiptgenerator;


    public receiptGeneratorController(receiptGenerator receiptgenerator) {
        this.receiptgenerator = receiptgenerator;
    }

    @GetMapping("/receipt")
    public void generateReceipt(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=booking_invoice_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.receiptgenerator.export(response);
    }
}
