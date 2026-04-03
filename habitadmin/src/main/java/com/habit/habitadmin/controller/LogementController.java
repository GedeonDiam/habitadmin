package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.service.LogementService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/habitago/logements")
public class LogementController {

    private static final Logger logger = Logger.getLogger(LogementController.class.getName());
    private final LogementService logementService;

    public LogementController(LogementService logementService) {
        this.logementService = logementService;
    }

    // Afficher la page de gestion des logements
    @GetMapping
    public String showLogementsPage(Model model) {
        List<Logement> logements = logementService.getAllLogements();
        model.addAttribute("logements", logements);
        model.addAttribute("totalLogements", logements.size());
        logger.info("✓ Page des logements affichée avec " + logements.size() + " logements");
        return "app/habitago/logements";
    }

    // API REST - Récupérer tous les logements
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Logement>> getAllLogements() {
        try {
            List<Logement> logements = logementService.getAllLogements();
            logger.info("✓ API GET /habitago/logements/api - " + logements.size() + " logements trouvés");
            return ResponseEntity.ok(logements);
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la récupération des logements: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Récupérer un logement par ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Logement> getLogementById(@PathVariable Long id) {
        try {
            return logementService.getLogementById(id)
                    .map(logement -> {
                        logger.info("✓ Logement ID:" + id + " trouvé");
                        return ResponseEntity.ok(logement);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la récupération du logement ID:" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Créer un logement
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Logement> createLogement(@RequestBody Logement logement) {
        try {
            logger.info("📝 Création d'un logement: " + logement.getAdresse());
            Logement savedLogement = logementService.saveLogement(logement);
            logger.info("✓ Logement créé avec ID: " + savedLogement.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLogement);
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la création du logement: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Mettre à jour un logement
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Logement> updateLogement(@PathVariable Long id, @RequestBody Logement logement) {
        try {
            Logement updated = logementService.updateLogement(id, logement);
            if (updated != null) {
                logger.info("✓ Logement ID:" + id + " modifié");
                return ResponseEntity.ok(updated);
            }
            logger.warning("⚠ Logement ID:" + id + " non trouvé");
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la modification du logement ID:" + id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Supprimer un logement
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteLogement(@PathVariable Long id) {
        try {
            logementService.deleteLogement(id);
            logger.info("✓ Logement ID:" + id + " supprimé");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la suppression du logement ID:" + id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
