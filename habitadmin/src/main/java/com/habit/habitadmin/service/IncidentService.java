package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Incident;
import com.habit.habitadmin.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Map<String, Long> getIncidentStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("OUVERT", incidentRepository.countByStatut("OUVERT"));
        stats.put("EN_COURS", incidentRepository.countByStatut("EN_COURS"));
        stats.put("RESOLU", incidentRepository.countByStatut("RESOLU"));
        stats.put("TOTAL", incidentRepository.count());
        return stats;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public List<Incident> getRecentIncidents() {
        return incidentRepository.findTop5ByOrderByDateCreationDesc();
    }
}
