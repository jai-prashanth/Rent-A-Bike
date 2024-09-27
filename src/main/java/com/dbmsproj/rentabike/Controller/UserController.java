package com.dbmsproj.rentabike.Controller;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Models.bookings;
import com.dbmsproj.rentabike.Repository.UserRepository;
import com.dbmsproj.rentabike.Repository.bookingsRepository;
import com.dbmsproj.rentabike.Service.userservice;
import com.dbmsproj.rentabike.security.SecurityServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.razorpay.*;

import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    private UserRepository userRepository;
    private userservice userService;
    private SecurityServices securityServices;
    private bookingsRepository bookingsRepo;
    @Autowired
    public UserController(UserRepository userRepository, userservice userService, SecurityServices securityServices,bookingsRepository bookingsRepo) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.securityServices=securityServices;
        this.bookingsRepo=bookingsRepo;
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(path = "/register")
    public String userRegistration(Model model) {
        model.addAttribute("User",new User());
        System.out.println("donewithuserregistration");
        return "register";
    }
    @PostMapping(path="/register")
    public String RegisterUser(User user){
        System.out.println(user.toString());
        user.setUserId(0L);
        userRepository.AddUser(user);
        System.out.println("User Added");
        return "home";
    }
    @RequestMapping(path="/")
    public String defaulthome() {
        return "home";
    }

    @GetMapping(path={"/login"})
    public String userlogIn(){
        System.out.println("started user login");
        return "login";
    }
//    @PostMapping(path="hom")

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(){
        User user=securityServices.findLoggedInUser();
        if(user!=null) {
            System.out.println("user not null");
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "/homeUser";
            }
        }
        return "home";
    }
    @GetMapping("/homeUser")
    public String homeUser()
    {
        return "homeUser";
    }
    @GetMapping("/profile")
    public String profile(Model model){
        User user=securityServices.findLoggedInUser();
        model.addAttribute("user",user);
        return "profile";
    }
    @GetMapping("/error")
    public String error(){return "error";}

    @GetMapping("/admin")
    public String admin(){return "admin";}
    @GetMapping("/about")
    public String about(){return "about";}

    @GetMapping("/contact")
    public String contact(){return "contact";}

    @GetMapping("/help")
    public String help(){return "help";}

    @GetMapping("/privacy")
    public String privacy(){return "privacy";}

    @GetMapping("/terms")
    public String terms(){return "terms";}

    @RequestMapping("/blog")
    public String blog(){
        return "blog";
    }
    public String bookBike(@RequestParam("reg_no") String reg_no, HttpSession session){
        session.setAttribute("reg_no", reg_no);
        return "payment";
    }

    @PostMapping("/payment")
    @ResponseBody
    public String createBooking(@RequestParam("amount") String data) throws Exception{
        System.out.println("Booking: createBooking");
        System.out.println();
        int amount = Integer.parseInt(data);

        RazorpayClient client = new RazorpayClient("rzp_test_sQ8dXiMJeBHGZW", "S0nAJGwlFXbzmxUXOQUYD3ve");
        JSONObject options = new JSONObject();
        options.put("amount", amount*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        Order order = client.orders.create(options);
        System.out.println(order);
        return order.toString();
    }
    @GetMapping("/bookings")
    public String getbookings(Model model){
        System.out.println("inside bookings");
        User user=securityServices.findLoggedInUser();
        System.out.println(user.getUsername());
        List<bookings> booking=bookingsRepo.findByUserId(user.getUserId());
        model.addAttribute("bookings",booking);
        return "bookings";
    }

    @GetMapping("/listofusers")
    public String getListOfUsers(Model model){
        List<User> listofusers=userRepository.getAllUsers();
        model.addAttribute("listofusers",listofusers);
        return "listofusers";
    }

}