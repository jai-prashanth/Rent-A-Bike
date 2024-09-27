package com.dbmsproj.rentabike.Controller;

import org.springframework.ui.Model;
import com.dbmsproj.rentabike.Models.bikesdeletelogs;
import com.dbmsproj.rentabike.Repository.bikesdeletelogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class bikesdeletelogsController {
    private final bikesdeletelogsRepository bikesdeletelogsRepo;
    @Autowired
    public bikesdeletelogsController(bikesdeletelogsRepository bikesdeletelogsRepo){
        this.bikesdeletelogsRepo=bikesdeletelogsRepo;
    }
    @GetMapping("/bikesdeletelogs")
    public String getAllBikeDeleteLogs(Model model){
        List<bikesdeletelogs> allBikeDeleteLogs=bikesdeletelogsRepo.getAllBikesDeleteLogs();
        model.addAttribute("allBikeDeleteLogs",allBikeDeleteLogs);
        return "bikesdeletelogs";
    }


}
