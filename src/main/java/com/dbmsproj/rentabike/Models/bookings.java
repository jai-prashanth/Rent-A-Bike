package com.dbmsproj.rentabike.Models;

import com.dbmsproj.rentabike.Repository.bikesRepository;
import com.dbmsproj.rentabike.Service.bikesService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class bookings {
    @Getter
    private Long bookingId;
    @Getter
    private Long customerId;
    @Getter
    private String RegistrationNumber;
    @Getter
    private LocalDateTime bookingTime;
    @Getter
    private LocalDateTime pickupTime;
    @Getter
    private LocalDateTime returnTime;
    private long downPayment;
    private long TotalPayment;
//    private int issuedBy;
//    @Getter
//    private String feedback;

    public bookings(
            Long bookingId, Long customerId, String registrationNumber, LocalDateTime bookingTime,
            LocalDateTime pickupTime, LocalDateTime returnTime,
            int issuedBy
    ) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.RegistrationNumber = registrationNumber;
        this.bookingTime = bookingTime;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
//        this.downPayment = (long) ((0.05)*payment());
//        this.TotalPayment = payment();
//        this.issuedBy = issuedBy;
//        this.feedback = feedback;
    }

    //    public long payment(){
//
//        LocalDateTime startdate=this.getPickupTime();
//        LocalDateTime enddate=this.getReturnTime();
//        Duration duration=Duration.between(startdate,enddate);
//        long hours = duration.toHours();
//
//        // Define your rate per hour
////        bikesRepository bR=null;
//        bikesRepository bikesrepository = new bikesRepository();
//        long ratePerHour=bikesrepository.getRentperHour(RegistrationNumber);
////        long ratePerHour = 40; // Change this to your actual rate
//        long pay=hours * ratePerHour;
//
//        // Calculate the payment
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
//          //  pay += additionalPayment;
//        }
//
//        return pay;
//    }
    public bookings(Long customerId, String registrationNumber, LocalDateTime bookingTime, LocalDateTime pickupTime, LocalDateTime returnTime, int downPayment, int totalPayment, int issuedBy, String feedback) {
        this.customerId = customerId;
        RegistrationNumber = registrationNumber;
        this.bookingTime = bookingTime;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.downPayment = downPayment;
        TotalPayment = totalPayment;
//        this.issuedBy = issuedBy;
//        this.feedback = feedback;
    }

    public int getDownPayment() {
        return 0;
    }

    public int getTotalPayment() {
        return (int)TotalPayment;
    }

//    public int getIssuedBy() {
//        return issuedBy;
//    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.RegistrationNumber = registrationNumber;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.TotalPayment = totalPayment;
    }

//    public void setIssuedBy(int issuedBy) {
//        this.issuedBy = issuedBy;
//    }

//    public void setFeedback(String feedback) {
//        this.feedback = feedback;
//    }
}
