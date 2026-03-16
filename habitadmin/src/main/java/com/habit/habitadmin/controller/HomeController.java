package com.habit.habitadmin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        // Rediriger vers le dashboard global après connexion
        return "redirect:/app/dashboard";
    }
    
    @GetMapping("/app/dashboard")
    public String dashboard() {
        return "app-dashboard";
    }
}
