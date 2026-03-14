package com.habit.habitadmin.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final LogementService logementService;
    private final ConsommationService consommationService;
    private final ContratService contratService;
    private final UtilisateurService utilisateurService;

    public DashboardService(LogementService logementService,
                           ConsommationService consommationService,
                           ContratService contratService,
                           UtilisateurService utilisateurService) {
        this.logementService = logementService;
        this.consommationService = consommationService;
        this.contratService = contratService;
        this.utilisateurService = utilisateurService;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Statistiques générales
        stats.put("totalLogements", logementService.getTotalLogements());
        stats.put("totalUtilisateurs", utilisateurService.getAllUsers().size());
        stats.put("totalContratsActifs", contratService.getContratStats().get("totalContratsActifs"));
        stats.put("loyerMoyen", contratService.getContratStats().get("loyerMoyen"));
        
        // Derniers logements
        stats.put("derniersLogements", logementService.getLatestLogements());
        
        // Statistiques de consommation
        stats.put("consommationStats", consommationService.getConsommationStats());
        
        // Statistiques des contrats
        stats.put("contratStats", contratService.getContratStats());
        
        return stats;
    }
}