package com.habit.habitadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur pour la gestion de l'application Habitago
 * Routes : /app/habitago/*
 */
@Controller
@RequestMapping("/app/habitago")
public class HabitagoController {

    /**
     * Page de gestion des utilisateurs
     * GET /app/habitago/users
     */
    @GetMapping("/users")
    public String users() {
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

    /**
     * Gestion des incidents
     * GET /app/habitago/incidents
     */
    // @GetMapping("/incidents")
    // public String incidents() {
    //     return "app/habitago/incidents";
    // }

    // /**
    //  * Gestion des intervenants
    //  * GET /app/habitago/intervenants
    //  */
    // @GetMapping("/intervenants")
    // public String intervenants() {
    //     return "app/habitago/intervenants";
    // }
}
