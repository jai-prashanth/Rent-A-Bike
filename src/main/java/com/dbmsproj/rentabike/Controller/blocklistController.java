package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.blocklist;
import com.dbmsproj.rentabike.Repository.blocklistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class blocklistController {
    private final blocklistRepository blocklistRepo;
    public blocklistController(blocklistRepository blocklistRepo){
        this.blocklistRepo=blocklistRepo;
    }

    @GetMapping("/blocklist")
    public String getAllBlocklist( Model model){
        List<blocklist> allBlocklist=blocklistRepo.getAllBlocklist();
        model.addAttribute("allBlocklist",blocklistRepo.getAllBlocklist());
        return "blocklist";
    }
}
