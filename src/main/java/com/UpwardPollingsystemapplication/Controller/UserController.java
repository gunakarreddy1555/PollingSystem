package com.UpwardPollingsystemapplication.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UpwardPollingsystemapplication.DTO.LoginDto;
import com.UpwardPollingsystemapplication.Entity.User;
import com.UpwardPollingsystemapplication.Entity.Vote;
import com.UpwardPollingsystemapplication.service.UserService;
import com.UpwardPollingsystemapplication.service.VoteService;


import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private VoteService voteservice;

    @GetMapping("/home")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registerpage";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerUser(@ModelAttribute User user) {
        Map<String, String> response = new HashMap<>();
        try {
            // Save the user (implement your saving logic here)
            service.saveUser(user);
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "failure");
        }
        return ResponseEntity.ok(response);  // Return the response as JSON
    }

    @GetMapping("/getLoginpage")
    public String getLoginPage() {
        return "Loginform";
    }

    @PostMapping("/login")
    @ResponseBody
    public String checkLoginDetails(@RequestParam String userid, @RequestParam String password, HttpSession session) {
        Optional<User> userOptional = service.checkUserDetailsByUseridandPassword(new LoginDto(userid, password));
        System.out.println("===========================================================================================");

        if (userOptional.isPresent()) {
            session.setAttribute("isLoggedIn", true); // Set session attribute for logged-in user
            System.out.println("hi from if ");
            return "loggedIn";
        } else {
        	System.out.println("byee from else ");
            return "Invalid";
        }
    }

    @GetMapping("/Polewindow")
    public String getPolewindow(Model model) {
    	 List<Vote> all = voteservice.geetALl();
    	model.addAttribute("allvotes", all);
        return "Polewindow";
    }

    @GetMapping("/check-login-status")
    @ResponseBody
    public String checkLoginStatus(HttpSession session) {
    	System.out.println("&&&&&&&&&&7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        System.out.println(isLoggedIn+"(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
        return (isLoggedIn != null && isLoggedIn) ? "loggedIn" : "notLoggedIn";
    }

}
