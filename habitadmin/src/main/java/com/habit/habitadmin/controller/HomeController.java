package com.habit.habitadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "app-dashboard";
    }
    
    @GetMapping("/app/dashboard")
    public String dashboard() {
        return "app-dashboard";
    }
}