
package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Models.bookings;
import com.dbmsproj.rentabike.Repository.UserRepository;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import com.dbmsproj.rentabike.Repository.bookingsRepository;
import com.dbmsproj.rentabike.Service.PaymentService;
import com.dbmsproj.rentabike.Service.bookingsService;

import com.dbmsproj.rentabike.Service.userservice;
import com.dbmsproj.rentabike.security.SecurityServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class BookingController {

    @Autowired
    private bookingsService bookingsservice;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private bikesRepository BikesRepo;
    @Autowired
    private bookingsRepository bookingsRepo;
    @Autowired
    private PaymentService paymentService;
    private SecurityServices securityServices;
    private userservice userService;
    @Autowired
    public BookingController(UserRepository userRepository, userservice userService, SecurityServices securityServices) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.securityServices=securityServices;
    }

    @GetMapping("/payment")
    public String bookBike(@RequestParam("reg_no") String reg_no, HttpSession session,Model model){
        session.setAttribute("reg_no", reg_no);
        LocalDateTime pickupDate = LocalDateTime.parse((session.getAttribute("pickupDate")).toString());
        LocalDateTime returnDate = LocalDateTime.parse((session.getAttribute("returnDate")).toString());
        long hours = (long) session.getAttribute("hours");
        int rent=   (int)BikesRepo.getRentperHour(reg_no);
        long amount = hours*rent;
        session.setAttribute("amount", amount);
        model.addAttribute("amount",amount);

        return "payment";
    }
    @GetMapping("/paymentsuccess")

    public String success(Model model,HttpSession session, @AuthenticationPrincipal UserDetails userDetails){
        String reg_no = (String)session.getAttribute("reg_no");
        LocalDateTime pickupDate = LocalDateTime.parse((session.getAttribute("pickupDate")).toString());
        LocalDateTime returnDate = LocalDateTime.parse((session.getAttribute("returnDate")).toString());
        bookings b = new bookings();
        b.setBookingTime(LocalDateTime.now());
        b.setPickupTime(pickupDate);
        b.setReturnTime(returnDate);
        b.setRegistrationNumber(reg_no);
        long rent=BikesRepo.getRentperHour(reg_no);
        b.setTotalPayment((int)rent);
        User user = userRepository.getUserByUsername(userDetails.getUsername());
        paymentService.payment(b);
//        b.setTotalPayment();
        b.setCustomerId(user.getUserId());
        bookingsRepo.insertBooking(b);
//        model.addAttribute("bookings",b);

        // create a booking with the above atteibutes
        return "redirect:/bookings";
    }
//    @GetMapping("/bookings/{username}")
//    public String getBookings(@PathVariable String username, Model model) {
//        List<bookings> bookings = bookingsservice.getBookings(username);
//        model.addAttribute("bookings", bookings);
//        return "bookingList";
//    }
//    @GetMapping("/bookings")
//    public String getbookings(){
//        System.out.println("inside bookings");
//        User user=securityServices.findLoggedInUser();
//        List<bookings> booking=bookingsRepo.findByUserId(user.getUserId());
//        return "bookings";
//    }
    @GetMapping("/allbookings")
    public String getAllBookings(Model model){
        List<bookings> allBookings=bookingsRepo.getAllBookings();
        model.addAttribute("allBookings",allBookings);
        System.out.println("inside allBookings");
        return "allBookings";
    }
}