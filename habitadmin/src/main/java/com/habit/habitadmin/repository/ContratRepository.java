package com.habit.habitadmin.repository;

import com.habit.habitadmin.model.Contrat;
import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByUtilisateur(Utilisateur utilisateur);
    List<Contrat> findByLogement(Logement logement);
    List<Contrat> findByStatut(String statut);
    
    @Query("SELECT AVG(c.montantLoyer) FROM Contrat c WHERE c.statut = 'ACTIF'")
    BigDecimal getAverageLoyer();
    
    @Query("SELECT COUNT(c) FROM Contrat c WHERE c.statut = 'ACTIF'")
    long countActiveContracts();
    
    @Query("SELECT c.statut, COUNT(c) FROM Contrat c GROUP BY c.statut")
    List<Object[]> getContractStats();
}