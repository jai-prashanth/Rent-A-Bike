package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.bikes;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BikeController {
    private final bikesRepository BikesRepo;
    @Autowired
    public BikeController(bikesRepository BikesRepo){
        this.BikesRepo=BikesRepo;
    }
    //<<<<<<< Updated upstream
//    @GetMapping("/availableBikes")
//    public String getAvailableBikes(@RequestParam(name = "hours", required = false) Integer hours,Model model){
//        System.out.println("Inside availableBikes");
//        if(hours!=null){
//            model.addAttribute("hours",hours);
//        }
//////=======
    @PostMapping("/availableBikes")
    public String getAvailableBikes(Model model, @RequestParam("pickupDate") LocalDateTime pickupDate, @RequestParam("returnDate") LocalDateTime returnDate, @RequestParam("hours") long hours, HttpSession session){
        String response = "You are under Blocklist because you have made more than 2 accidents";
        int accidental = BikesRepo.getTotalNumberofAccidents();
        if(accidental>2)
        {
            model.addAttribute("message",response);
            System.out.println("Not a valid coustomer");
            return "message";
        }
        System.out.println("Inside availableBikes");
        System.out.println(pickupDate);
        System.out.println(returnDate);
        session.setAttribute("pickupDate", pickupDate);
        session.setAttribute("returnDate", returnDate);
        session.setAttribute("hours",hours);
////>>>>>>> Stashed changes
        if(hours!=0) {
            model.addAttribute("hours", hours);
        }
        List<bikes> availableBikes=BikesRepo.getAvailableBikesBetweenDates(pickupDate, returnDate);
        model.addAttribute("availableBikes",availableBikes);
        return "availableBikes";
    }

    @GetMapping("/reg")
    public String sdfgh(){
        long rent=BikesRepo.getRentperHour("1234567");
        System.out.println(rent);
        return "dfg";
    }

    @GetMapping("/bikes")
    public String getAllBikes(Model model){
        List<bikes> allBikes=BikesRepo.getAllBikes();
        model.addAttribute("allBikes",allBikes);
        return "bikes";
    }
    @PostMapping("/addBike")
    public String addBike(
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("bikeModel") String bikeModel,
            @RequestParam("bikeStatus") String bikeStatus,
            @RequestParam("CBookNumber") String CBookNumber,
            @RequestParam("Insurance") String Insurance,
            @RequestParam("isAvailable") boolean isAvailable,
            @RequestParam("ratePerHour") long ratePerHour,
            Model model
    ){
        System.out.println("inside addBike");
        bikes bike=new bikes(registrationNumber,bikeModel,bikeStatus,CBookNumber,Insurance,isAvailable,ratePerHour);
        BikesRepo.insertBike(bike);
        List<bikes> allBikes=BikesRepo.getAllBikes();
        model.addAttribute("allBikes",allBikes);

        return "bikes";
    }
    @PostMapping("/updateBike")
    public String updateBike(
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("isAvailable") boolean isAvailable,
            @RequestParam("ratePerHour") long ratePerHour,
            Model model
    ){
        System.out.println("inside updateBike");
        bikes bike=new bikes(registrationNumber,isAvailable,ratePerHour);
        BikesRepo.updateBike(bike);
        List<bikes> allBikes=BikesRepo.getAllBikes();
        model.addAttribute("allBikes",allBikes);

        return "bikes";
    }
    @PostMapping("/deleteBike")
    public String deleteBike(@RequestParam("registrationNumber") String registrationNumber,Model model){
        System.out.println("inside deleteBike");
        bikes bike=new bikes(registrationNumber);
        BikesRepo.deleteBike(bike);
        List<bikes> allBikes=BikesRepo.getAllBikes();
        model.addAttribute("allBikes",allBikes);
        return "bikes";
    }
    @GetMapping("/addBike")
    public String goToAddBike(){
        return "addBike";
    }
    @GetMapping("/updateBike")
    public String goToupdateBike(){
        return "updateBike";
    }
    @GetMapping("/deleteBike")
    public String goTodeleteBike(){
        return "deleteBike";
    }
//    @PostMapping("/addBike")
//    public String bike(){
//        return "bikes";
//    }


//    @GetMapping("/payment")
//    public String goToPayment(){
//        return "payment";
//    }
}