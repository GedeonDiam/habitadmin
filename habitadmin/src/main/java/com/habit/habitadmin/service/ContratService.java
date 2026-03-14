package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Contrat;
import com.habit.habitadmin.repository.ContratRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContratService {

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public Contrat saveContrat(Contrat contrat) {
        contrat.setDateCreation(LocalDateTime.now());
        return contratRepository.save(contrat);
    }

    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    public Map<String, Object> getContratStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalContratsActifs", contratRepository.countActiveContracts());
        stats.put("loyerMoyen", contratRepository.getAverageLoyer());
        
        // Statistiques par statut
        List<Object[]> contractStats = contratRepository.getContractStats();
        Map<String, Long> statsMap = new HashMap<>();
        for (Object[] stat : contractStats) {
            statsMap.put((String) stat[0], (Long) stat[1]);
        }
        stats.put("parStatut", statsMap);
        
        return stats;
    }
}