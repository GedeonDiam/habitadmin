package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.repository.LogementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LogementService {
    @Autowired
    private LogementRepository logementRepository;

    public List<Logement> getAllLogements() {
        return logementRepository.findAll();
    }

    public Optional<Logement> getLogementById(Long id) {
        return logementRepository.findById(id);
    }

    public Logement createLogement(Logement logement) {
        return logementRepository.save(logement);
    }

    public Logement updateLogement(Long id, Logement logementDetails) {
        Optional<Logement> logement = logementRepository.findById(id);
        if (logement.isPresent()) {
            Logement logementToUpdate = logement.get();
            logementToUpdate.setAdresse(logementDetails.getAdresse());
            logementToUpdate.setVille(logementDetails.getVille());
            logementToUpdate.setCodePostal(logementDetails.getCodePostal());
            logementToUpdate.setTypeLogement(logementDetails.getTypeLogement());
            return logementRepository.save(logementToUpdate);
        }
        return null;
    }

    public void deleteLogement(Long id) {
        logementRepository.deleteById(id);
    }

    public List<Logement> searchByVille(String ville) {
        return logementRepository.findByVille(ville);
    }

    public List<Logement> searchByTypeLogement(String typeLogement) {
        return logementRepository.findByTypeLogement(typeLogement);
    }
}
