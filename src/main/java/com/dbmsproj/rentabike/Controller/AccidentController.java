package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.Accidents;
import com.dbmsproj.rentabike.Models.bikes;
import com.dbmsproj.rentabike.Repository.AccidentsRepository;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class AccidentController {
    private final AccidentsRepository accidentRepository;

    @Autowired
    public AccidentController(AccidentsRepository accidentsRepository){
        this.accidentRepository=accidentsRepository;
    }
    
    

    @GetMapping("/addAccident")
    public String goToaccidents(){
        return "addAccident";
    }
    @PostMapping("/addAccident")
    public String addAccident(@RequestParam("registrationNumber") String registrationNumber,
                              @RequestParam("userId") Long userId,
                              @RequestParam("accidentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date accidentDate,
                              @RequestParam("accidentLocation") String accidentLocation,
                              @RequestParam("accidentDescription") String accidentDescription,
                              Model model
                              ){
        System.out.println("inside add Accident");
        Accidents accidents=new Accidents(registrationNumber,userId,accidentDate,accidentLocation,accidentDescription);
        accidentRepository.insertaccident(accidents);
        List<Accidents> allAccidents=accidentRepository.getAllAccidents();
        model.addAttribute("allAccidents",allAccidents);
        System.out.println("Accident Insertion Successful");
        return "accidents";
    }

    @GetMapping("/accidents")
    public String getAllBikes(Model model){
        List<Accidents> allAccidents=accidentRepository.getAllAccidents();
        model.addAttribute("allAccidents",allAccidents);
        return "accidents";
    }
    
}
