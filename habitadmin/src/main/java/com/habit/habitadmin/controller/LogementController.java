package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.service.LogementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app/habitago/logements")
public class LogementController {

    private final LogementService logementService;

    public LogementController(LogementService logementService) {
        this.logementService = logementService;
    }

    @GetMapping
    public String showPropertiesPage(Model model) {
        model.addAttribute("logements", logementService.getAllLogements());
        model.addAttribute("totalLogements", logementService.getTotalLogements());
        return "app/habitago/properties";
    }

    // API REST
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Logement>> getAllLogements() {
        return ResponseEntity.ok(logementService.getAllLogements());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Logement> getLogementById(@PathVariable Long id) {
        return logementService.getLogementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Logement> createLogement(@RequestBody Logement logement) {
        return ResponseEntity.ok(logementService.saveLogement(logement));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Logement> updateLogement(@PathVariable Long id, @RequestBody Logement logement) {
        Logement updated = logementService.updateLogement(id, logement);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteLogement(@PathVariable Long id) {
        logementService.deleteLogement(id);
        return ResponseEntity.noContent().build();
    }
}
