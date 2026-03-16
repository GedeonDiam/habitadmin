package com.habit.habitadmin.repository;

import com.habit.habitadmin.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    
    @Query("SELECT i.statut, COUNT(i) FROM Incident i GROUP BY i.statut")
    List<Object[]> getIncidentStats();
    
    long countByStatut(String statut);

    List<Incident> findTop5ByOrderByDateCreationDesc();
}
