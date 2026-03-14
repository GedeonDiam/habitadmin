package com.habit.habitadmin.repository;

import com.habit.habitadmin.model.Consommation;
import com.habit.habitadmin.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ConsommationRepository extends JpaRepository<Consommation, Long> {
    List<Consommation> findByLogement(Logement logement);
    
    @Query("SELECT AVG(c.valeur) FROM Consommation c WHERE c.type = :type AND c.validee = true")
    BigDecimal getAverageConsommationByType(@Param("type") String type);
    
    @Query("SELECT c.type, SUM(c.valeur) FROM Consommation c GROUP BY c.type")
    List<Object[]> getTotalConsommationByType();
    
    @Query("SELECT MONTH(c.periodeDebut), SUM(c.valeur) FROM Consommation c " +
           "WHERE YEAR(c.periodeDebut) = YEAR(CURRENT_DATE) " +
           "GROUP BY MONTH(c.periodeDebut) ORDER BY MONTH(c.periodeDebut)")
    List<Object[]> getMonthlyConsommation();
}