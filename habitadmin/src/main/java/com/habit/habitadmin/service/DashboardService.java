package com.habit.habitadmin.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DashboardService {

    private final LogementService logementService;
    private final ConsommationService consommationService;
    private final ContratService contratService;
    private final UtilisateurService utilisateurService;
    private final IncidentService incidentService;

    public DashboardService(LogementService logementService,
                           ConsommationService consommationService,
                           ContratService contratService,
                           UtilisateurService utilisateurService,
                           IncidentService incidentService) {
        this.logementService = logementService;
        this.consommationService = consommationService;
        this.contratService = contratService;
        this.utilisateurService = utilisateurService;
        this.incidentService = incidentService;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Statistiques générales
        long totalLogements = logementService.getTotalLogements();
        
        // Données réelles depuis la BDD
        List<Long> occupiedLogementIds = contratService.getOccupiedLogementIds();
        long locatairesCount = utilisateurService.getTotalUsers();
        long totalContrats = contratService.getAllContrats().size();
        long availableLogements = Math.max(0, totalLogements - occupiedLogementIds.size());
        
        stats.put("totalLogements", totalLogements);
        stats.put("totalUtilisateurs", locatairesCount); 
        stats.put("totalContrats", totalContrats);
        stats.put("contratsActifs", contratService.getContratStats().get("totalContratsActifs"));
        stats.put("occupiedLogements", occupiedLogementIds.size());
        stats.put("availableLogements", availableLogements);
        stats.put("loyerMoyen", contratService.getContratStats().get("loyerMoyen"));
        
        // Incidents
        stats.put("incidentStats", incidentService.getIncidentStats());
        stats.put("recentIncidents", incidentService.getRecentIncidents());
        
        // Échéances de contrats
        stats.put("upcomingExpirations", contratService.getExpiringSoon());
        
        // Derniers logements
        List<com.habit.habitadmin.model.Logement> derniersLogements = logementService.getLatestLogements();
        stats.put("derniersLogements", derniersLogements);
        
        // Liste des IDs occupés pour le template
        stats.put("occupiedLogementIds", occupiedLogementIds);
        
        // Statistiques de consommation
        stats.put("consommationStats", consommationService.getConsommationStats());
        
        // Statistiques des contrats
        stats.put("contratStats", contratService.getContratStats());
        
        return stats;
    }
}