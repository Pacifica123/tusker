package com.example.tusker.controllers;

import com.example.tusker.dto.UserRegistrationDto;
import com.example.tusker.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reg")
public class RegistrationController {
    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }
    @PostMapping()
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
        return "redirect:/login"; // Перенаправление на страницу входа
    }
}
