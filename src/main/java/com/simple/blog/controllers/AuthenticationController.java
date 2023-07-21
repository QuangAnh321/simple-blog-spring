package com.simple.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.blog.models.dtos.LoginDTO;
import com.simple.blog.models.dtos.RegisterDTO;
import com.simple.blog.services.security.*;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(RegisterDTO registerDTO, Model model) {
        model.addAttribute("registerDTO", registerDTO);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterDTO registerDTO) {
        authenticationService.registerNewUser(registerDTO);
        return "redirect:/login";
    }

}
