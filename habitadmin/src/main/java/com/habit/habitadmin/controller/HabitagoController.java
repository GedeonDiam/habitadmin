package com.habit.habitadmin.controller;

import com.habit.habitadmin.service.UtilisateurService;
import com.habit.habitadmin.service.LogementService;
import com.habit.habitadmin.service.ContratService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/habitago")
public class HabitagoController {

    private final UtilisateurService utilisateurService;
    private final LogementService logementService;
    private final ContratService contratService;

    public HabitagoController(UtilisateurService utilisateurService, LogementService logementService, ContratService contratService) {
        this.utilisateurService = utilisateurService;
        this.logementService = logementService;
        this.contratService = contratService;
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
    public String contracts(Model model) {
        model.addAttribute("contrats", contratService.getAllContrats());
        model.addAttribute("totalContrats", contratService.getAllContrats().size());
        return "app/habitago/contracts";
    }

    /**
     * Gestion des logements
     * GET /app/habitago/logements
     */
    @GetMapping("/logements")
    public String logements(Model model) {
        model.addAttribute("logements", logementService.getAllLogements());
        model.addAttribute("totalLogements", logementService.getAllLogements().size());
        return "app/habitago/logements";
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
     * Page de paramètres
     * GET /app/habitago/settings
     */
    @GetMapping("/settings")
    public String settings() {
        return "app/habitago/settings";
    }
}