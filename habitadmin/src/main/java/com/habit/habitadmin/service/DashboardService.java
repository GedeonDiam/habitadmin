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
        
        // Logique demandée : UN SEUL logement occupé
        // Locataires = 1
        // Contrats = Total des logements disponibles (Total - 1)
        long occupiedCount = (totalLogements > 0) ? 1 : 0;
        long locatairesCount = occupiedCount;
        long availableContractsCount = (totalLogements > 0) ? totalLogements - 1 : 0;
        
        stats.put("totalLogements", totalLogements);
        stats.put("totalUtilisateurs", locatairesCount); 
        stats.put("totalContratsActifs", availableContractsCount);
        stats.put("loyerMoyen", contratService.getContratStats().get("loyerMoyen"));
        
        // Incidents
        stats.put("incidentStats", incidentService.getIncidentStats());
        stats.put("recentIncidents", incidentService.getRecentIncidents());
        
        // Échéances de contrats
        stats.put("upcomingExpirations", contratService.getExpiringSoon());
        
        // Derniers logements
        List<com.habit.habitadmin.model.Logement> derniersLogements = logementService.getLatestLogements();
        stats.put("derniersLogements", derniersLogements);
        
        // Sélectionner UN SEUL logement aléatoire à marquer comme OCCUPÉ
        List<Long> singleOccupiedId = new ArrayList<>();
        if (derniersLogements != null && !derniersLogements.isEmpty()) {
            int randomIndex = new Random().nextInt(derniersLogements.size());
            singleOccupiedId.add(derniersLogements.get(randomIndex).getId());
        }
        stats.put("occupiedLogementIds", singleOccupiedId);
        
        // Statistiques de consommation
        stats.put("consommationStats", consommationService.getConsommationStats());
        
        // Statistiques des contrats
        stats.put("contratStats", contratService.getContratStats());
        
        return stats;
    }
}