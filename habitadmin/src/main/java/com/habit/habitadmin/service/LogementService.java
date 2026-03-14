package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.repository.LogementRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogementService {

    private final LogementRepository logementRepository;

    public LogementService(LogementRepository logementRepository) {
        this.logementRepository = logementRepository;
    }

    public List<Logement> getAllLogements() {
        return logementRepository.findAll();
    }

    public Optional<Logement> getLogementById(Long id) {
        return logementRepository.findById(id);
    }

    public Logement saveLogement(Logement logement) {
        logement.setDateCreation(LocalDateTime.now());
        logement.setDateModification(LocalDateTime.now());
        return logementRepository.save(logement);
    }

    public Logement updateLogement(Long id, Logement logementDetails) {
        Optional<Logement> logementOpt = logementRepository.findById(id);
        
        if (logementOpt.isPresent()) {
            Logement logement = logementOpt.get();
            logement.setAdresse(logementDetails.getAdresse());
            logement.setVille(logementDetails.getVille());
            logement.setCodePostal(logementDetails.getCodePostal());
            logement.setTypeLogement(logementDetails.getTypeLogement());
            logement.setDateModification(LocalDateTime.now());
            return logementRepository.save(logement);
        }
        return null;
    }

    public void deleteLogement(Long id) {
        logementRepository.deleteById(id);
    }

    public long getTotalLogements() {
        return logementRepository.count();
    }

    public List<Logement> getLatestLogements() {
        return logementRepository.findLatestLogements();
    }
}