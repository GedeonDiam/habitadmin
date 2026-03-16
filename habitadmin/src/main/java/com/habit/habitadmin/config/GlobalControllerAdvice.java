package com.habit.habitadmin.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        
        if (uri.equals("/app/dashboard") || uri.equals("/")) {
            model.addAttribute("activeSection", "global");
        } else if (uri.startsWith("/app/habitago")) {
            model.addAttribute("activeApp", "habitago");
            if (uri.contains("/dashboard")) {
                model.addAttribute("activeSection", "dashboard");
            } else if (uri.contains("/users")) {
                model.addAttribute("activeSection", "users");
            } else if (uri.contains("/logements")) {
                model.addAttribute("activeSection", "logements");
            } else if (uri.contains("/contracts")) {
                model.addAttribute("activeSection", "contracts");
            }
        } else if (uri.startsWith("/app/habitagreen")) {
            model.addAttribute("activeApp", "habitagreen");
            if (uri.contains("/dashboard")) {
                model.addAttribute("activeSection", "dashboard");
            } else if (uri.contains("/consumption")) {
                model.addAttribute("activeSection", "consumption");
            }
        }
    }
}
