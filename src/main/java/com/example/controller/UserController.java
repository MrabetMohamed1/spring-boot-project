package com.example.controller;

import com.example.models.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model) {
        try {
            userService.save(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register"; // show registration page with error
        }
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
