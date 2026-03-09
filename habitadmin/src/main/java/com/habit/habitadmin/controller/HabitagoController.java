package com.habit.habitadmin.controller;

import com.habit.habitadmin.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur pour la gestion de l'application Habitago
 * Routes : /app/habitago/*
 */
@Controller
@RequestMapping("/app/habitago")
public class HabitagoController {

    private final UtilisateurService utilisateurService;

    public HabitagoController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Page de gestion des utilisateurs
     * GET /app/habitago/users
     */
    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("users", utilisateurService.getAllUsers());
        model.addAttribute("totalUsers", utilisateurService.getAllUsers().size());

        return "app/habitago/users";
    }

    /**
     * Dashboard Habitago
     * GET /app/habitago/dashboard
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "app/habitago/dashboard";
    }

    /**
     * Gestion des propriétés
     * GET /app/habitago/properties
     */
    @GetMapping("/properties")
    public String properties() {
        return "app/habitago/properties";
    }

    /**
     * Gestion des contrats
     * GET /app/habitago/contracts
     */
    @GetMapping("/contracts")
    public String contracts() {
        return "app/habitago/contracts";
    }

    /**
     * Gestion des paiements
     * GET /app/habitago/payments
     */
    @GetMapping("/payments")
    public String payments() {
        return "app/habitago/payments";
    }

}