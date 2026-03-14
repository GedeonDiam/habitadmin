package com.habit.habitadmin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        // Si l'utilisateur n'est pas connecté, le rediriger vers la page de login
        // (ceci est géré par Spring Security avec authorizeHttpRequests)
        // Si connecté, rediriger vers le dashboard
        return "redirect:/app/habitago/dashboard";
    }
    
    @GetMapping("/app/dashboard")
    public String dashboard() {
        return "app-dashboard";
    }
}
