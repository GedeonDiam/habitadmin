package com.habit.habitadmin.repository;

import com.habit.habitadmin.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {
    List<Logement> findByVille(String ville);
    
    @Query("SELECT l FROM Logement l ORDER BY l.dateCreation DESC")
    List<Logement> findLatestLogements();
    
    @Query("SELECT COUNT(l) FROM Logement l")
    long countTotalLogements();
}