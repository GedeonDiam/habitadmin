package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.service.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app/logements")
public class LogementController {
    @Autowired
    private LogementService logementService;

    @GetMapping
    public String listLogements(Model model) {
        List<Logement> logements = logementService.getAllLogements();
        model.addAttribute("logements", logements);
        return "app/logements";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("logement", new Logement());
        return "app/habitago/property-form";
    }

    @PostMapping
    public String createLogement(@ModelAttribute Logement logement) {
        logementService.createLogement(logement);
        return "redirect:/app/logements";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Logement> logement = logementService.getLogementById(id);
        if (logement.isPresent()) {
            model.addAttribute("logement", logement.get());
            return "app/habitago/property-form";
        }
        return "redirect:/app/logements";
    }

    @PostMapping("/{id}")
    public String updateLogement(@PathVariable Long id, @ModelAttribute Logement logement) {
        logementService.updateLogement(id, logement);
        return "redirect:/app/logements";
    }

    @GetMapping("/{id}/delete")
    public String deleteLogement(@PathVariable Long id) {
        logementService.deleteLogement(id);
        return "redirect:/app/logements";
    }

    @GetMapping("/search")
    public String searchLogements(@RequestParam(required = false) String ville,
                                  @RequestParam(required = false) String typeLogement,
                                  Model model) {
        List<Logement> logements;
        if (ville != null && !ville.isEmpty()) {
            logements = logementService.searchByVille(ville);
        } else if (typeLogement != null && !typeLogement.isEmpty()) {
            logements = logementService.searchByTypeLogement(typeLogement);
        } else {
            logements = logementService.getAllLogements();
        }
        model.addAttribute("logements", logements);
        return "app/logements";
    }
}
