package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Consommation;
import com.habit.habitadmin.service.ConsommationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consommations")
public class ConsommationController {

    private final ConsommationService consommationService;

    public ConsommationController(ConsommationService consommationService) {
        this.consommationService = consommationService;
    }

    @GetMapping
    public ResponseEntity<List<Consommation>> getAllConsommations() {
        return ResponseEntity.ok(consommationService.getAllConsommations());
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getConsommationStats() {
        return ResponseEntity.ok(consommationService.getConsommationStats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consommation> getConsommationById(@PathVariable Long id) {
        return consommationService.getConsommationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consommation> createConsommation(@RequestBody Consommation consommation) {
        // Note: Il faut aussi envoyer l'ID du logement dans la requête
        // Pour l'instant, on utilise une méthode temporaire
        Consommation saved = consommationService.saveConsommation(consommation, 1L); // ID temporaire
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsommation(@PathVariable Long id) {
        consommationService.deleteConsommation(id);
        return ResponseEntity.noContent().build();
    }
}