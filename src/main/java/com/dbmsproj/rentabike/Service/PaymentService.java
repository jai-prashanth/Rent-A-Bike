package com.dbmsproj.rentabike.Service;

import com.dbmsproj.rentabike.Models.bookings;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    bikesRepository br;

    public long payment(bookings b){

        LocalDateTime startdate=b.getPickupTime();
        LocalDateTime enddate=b.getReturnTime();
        Duration duration=Duration.between(startdate,enddate);
        long hours = duration.toHours();

        // Define your rate per hour
//        bikesRepository bR=null;
        long ratePerHour=br.getRentperHour(b.getRegistrationNumber());
//        long ratePerHour = 40; // Change this to your actual rate
        long pay=hours * ratePerHour;

        // Calculate the payment
//        if (hours > 12) {
//            // Calculate the additional hours
//            long additionalHours = hours - 12;
//
//            // Calculate the payment for additional hours
//            long additionalPayment = additionalHours * ratePerHour;
//
//            // Apply a 5% discount to the additional payment
//            double discount = 0.05; // 5% discount
//            pay -= (long) (additionalPayment * discount);
//
//            // Add the additional payment to the total payment
//            //  pay += additionalPayment;
//        }
        b.setTotalPayment((int)pay);
        b.setDownPayment((0));
        return pay;
    }
}