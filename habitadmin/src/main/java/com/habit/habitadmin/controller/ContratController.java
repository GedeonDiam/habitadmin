package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Contrat;
import com.habit.habitadmin.service.ContratService;
import com.habit.habitadmin.service.UtilisateurService;
import com.habit.habitadmin.service.LogementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/habitago/contracts")
public class ContratController {

    private static final Logger logger = Logger.getLogger(ContratController.class.getName());

    private final ContratService contratService;
    private final UtilisateurService utilisateurService;
    private final LogementService logementService;

    public ContratController(ContratService contratService, UtilisateurService utilisateurService, LogementService logementService) {
        this.contratService = contratService;
        this.utilisateurService = utilisateurService;
        this.logementService = logementService;
    }

    // Afficher la page de gestion des contrats
    @GetMapping
    public String showContractsPage(Model model) {
        List<Contrat> contrats = contratService.getAllContrats();
        Map<String, Object> stats = contratService.getContratStats();
        model.addAttribute("contrats", contrats);
        model.addAttribute("stats", stats);
        return "app/habitago/contracts";
    }

    // API REST - Récupérer tous les contrats
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Contrat>> getAllContrats() {
        try {
            List<Contrat> contrats = contratService.getAllContrats();
            logger.info("✓ API GET /habitago/contracts/api - " + contrats.size() + " contrats trouvés");
            return ResponseEntity.ok(contrats);
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la récupération des contrats: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Ajouter un contrat
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat) {
        try {
            logger.info("📝 Création d'un contrat pour utilisateur ID: " + contrat.getUtilisateur().getId());
            Contrat savedContrat = contratService.saveContrat(contrat);
            logger.info("✓ Contrat créé avec ID: " + savedContrat.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedContrat);
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la création du contrat: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Récupérer un contrat par ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        try {
            return contratService.getContratById(id)
                    .map(contrat -> {
                        logger.info("✓ Contrat ID:" + id + " trouvé");
                        return ResponseEntity.ok(contrat);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la récupération du contrat ID:" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Mettre à jour un contrat
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Contrat> updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        try {
            Contrat existingContrat = contratService.getContratById(id).orElse(null);
            if (existingContrat == null) {
                logger.warning("⚠ Contrat ID:" + id + " non trouvé");
                return ResponseEntity.notFound().build();
            }
            
            existingContrat.setUtilisateur(contrat.getUtilisateur());
            existingContrat.setLogement(contrat.getLogement());
            existingContrat.setDateDebut(contrat.getDateDebut());
            existingContrat.setDateFin(contrat.getDateFin());
            existingContrat.setMontantLoyer(contrat.getMontantLoyer());
            existingContrat.setMontantCaution(contrat.getMontantCaution());
            existingContrat.setStatut(contrat.getStatut());
            
            Contrat updatedContrat = contratService.saveContrat(existingContrat);
            logger.info("✓ Contrat ID:" + id + " modifié");
            return ResponseEntity.ok(updatedContrat);
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la modification du contrat ID:" + id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API REST - Supprimer un contrat
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        try {
            contratService.deleteContrat(id);
            logger.info("✓ Contrat ID:" + id + " supprimé");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.severe("✗ Erreur lors de la suppression du contrat ID:" + id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
