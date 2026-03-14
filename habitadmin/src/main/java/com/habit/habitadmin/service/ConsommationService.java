package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Consommation;
import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.repository.ConsommationRepository;
import com.habit.habitadmin.repository.LogementRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConsommationService {

    private final ConsommationRepository consommationRepository;
    private final LogementRepository logementRepository;

    public ConsommationService(ConsommationRepository consommationRepository, 
                               LogementRepository logementRepository) {
        this.consommationRepository = consommationRepository;
        this.logementRepository = logementRepository;
    }

    public List<Consommation> getAllConsommations() {
        return consommationRepository.findAll();
    }

    public Optional<Consommation> getConsommationById(Long id) {
        return consommationRepository.findById(id);
    }

    public Consommation saveConsommation(Consommation consommation, Long logementId) {
        Logement logement = logementRepository.findById(logementId)
            .orElseThrow(() -> new RuntimeException("Logement non trouvé"));
        
        consommation.setLogement(logement);
        consommation.setDateCreation(LocalDateTime.now());
        return consommationRepository.save(consommation);
    }

    public void deleteConsommation(Long id) {
        consommationRepository.deleteById(id);
    }

    public Map<String, Object> getConsommationStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Moyennes par type
        stats.put("moyenneElectricite", 
            consommationRepository.getAverageConsommationByType("ELECTRICITE"));
        stats.put("moyenneEau", 
            consommationRepository.getAverageConsommationByType("EAU"));
        stats.put("moyenneGaz", 
            consommationRepository.getAverageConsommationByType("GAZ"));
        
        // Totaux par type
        List<Object[]> totals = consommationRepository.getTotalConsommationByType();
        Map<String, BigDecimal> totalsMap = new HashMap<>();
        for (Object[] total : totals) {
            totalsMap.put((String) total[0], (BigDecimal) total[1]);
        }
        stats.put("totaux", totalsMap);
        
        // Données mensuelles pour les graphiques
        stats.put("donneesMensuelles", consommationRepository.getMonthlyConsommation());
        
        return stats;
    }
}