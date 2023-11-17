package com.example.tusker.controllers;

import com.example.tusker.dto.UserLoginDto;
import com.example.tusker.dto.UserRegistrationDto;
import com.example.tusker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(Model model){
        model.addAttribute("user", new UserLoginDto());
        return "login";
    }
    @PostMapping
    public String loginUser(@ModelAttribute("user") UserLoginDto loginDto, HttpServletRequest request){
        try {
            // Authenticate the user
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect to success page after successful login
            return "successLogin";
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return "redirect:/login?error";
        }
    }

}
