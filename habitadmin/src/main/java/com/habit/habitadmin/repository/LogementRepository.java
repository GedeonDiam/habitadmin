package com.habit.habitadmin.repository;

import com.habit.habitadmin.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {
    List<Logement> findByVille(String ville);
    List<Logement> findByTypeLogement(String typeLogement);
}
