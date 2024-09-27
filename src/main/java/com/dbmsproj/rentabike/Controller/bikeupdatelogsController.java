package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.bikeupdatelogs;
import com.dbmsproj.rentabike.Repository.bikesRepository;
import com.dbmsproj.rentabike.Repository.bikeupdatelogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class bikeupdatelogsController {
    private final bikeupdatelogsRepository bikeupdatelogsRepo;
    @Autowired
    public bikeupdatelogsController(bikeupdatelogsRepository bikeupdatelogsRepo){
        this.bikeupdatelogsRepo=bikeupdatelogsRepo;
    }
    @GetMapping("/bikeupdatelogs")
    public String getAllBikeUpdateLogs(Model model){
        List<bikeupdatelogs> allBikeUpdateLogs=bikeupdatelogsRepo.getAllBikeUpdateLogs();
        model.addAttribute("allBikeUpdateLogs",allBikeUpdateLogs);
        return "bikeupdatelogs";
    }

}
