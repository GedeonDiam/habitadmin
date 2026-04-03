package com.habit.habitadmin.init;

import com.habit.habitadmin.model.Utilisateur;
import com.habit.habitadmin.model.Logement;
import com.habit.habitadmin.model.Contrat;
import com.habit.habitadmin.repository.UtilisateurRepository;
import com.habit.habitadmin.repository.LogementRepository;
import com.habit.habitadmin.repository.ContratRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final LogementRepository logementRepository;
    private final ContratRepository contratRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UtilisateurRepository utilisateurRepository, LogementRepository logementRepository, ContratRepository contratRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.logementRepository = logementRepository;
        this.contratRepository = contratRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifier s'il y a déjà des utilisateurs
        if (utilisateurRepository.count() == 0) {
            // Créer un utilisateur admin par défaut
            Utilisateur admin = new Utilisateur();
            admin.setEmail("admin@habitago.fr");
            admin.setNom("Dupont");
            admin.setPrenom("Jean");
            admin.setRole("admin");
            // Encoder le mot de passe : "admin123"
            admin.setMotDePasse(passwordEncoder.encode("admin123"));
            admin.setDateCreation(LocalDateTime.now());
            admin.setDateModification(LocalDateTime.now());

            utilisateurRepository.save(admin);
            System.out.println("✅ Utilisateur admin par défaut créé !");
            System.out.println("   Email: admin@habitago.fr");
            System.out.println("   Mot de passe: admin123");
        }

        // Créer des données de test pour les contrats
        if (contratRepository.count() == 0) {
            // Créer des utilisateurs de test
            Utilisateur user1 = new Utilisateur();
            user1.setEmail("jean.dupont@example.com");
            user1.setPrenom("Jean");
            user1.setNom("Dupont");
            user1.setRole("locataire");
            user1.setMotDePasse(passwordEncoder.encode("password123"));
            user1.setDateCreation(LocalDateTime.now());
            utilisateurRepository.save(user1);

            Utilisateur user2 = new Utilisateur();
            user2.setEmail("marie.martin@example.com");
            user2.setPrenom("Marie");
            user2.setNom("Martin");
            user2.setRole("locataire");
            user2.setMotDePasse(passwordEncoder.encode("password123"));
            user2.setDateCreation(LocalDateTime.now());
            utilisateurRepository.save(user2);

            Utilisateur user3 = new Utilisateur();
            user3.setEmail("pierre.bernard@example.com");
            user3.setPrenom("Pierre");
            user3.setNom("Bernard");
            user3.setRole("proprietaire");
            user3.setMotDePasse(passwordEncoder.encode("password123"));
            user3.setDateCreation(LocalDateTime.now());
            utilisateurRepository.save(user3);

            System.out.println("✅ Utilisateurs de test créés !");

            // Créer des logements de test
            Logement logement1 = new Logement();
            logement1.setAdresse("123 rue de la Paix");
            logement1.setVille("Paris");
            logement1.setCodePostal("75001");
            logement1.setTypeLogement("Appartement");
            logement1.setDateCreation(LocalDateTime.now());
            logementRepository.save(logement1);

            Logement logement2 = new Logement();
            logement2.setAdresse("456 avenue du Soleil");
            logement2.setVille("Lyon");
            logement2.setCodePostal("69000");
            logement2.setTypeLogement("Maison");
            logement2.setDateCreation(LocalDateTime.now());
            logementRepository.save(logement2);

            Logement logement3 = new Logement();
            logement3.setAdresse("789 boulevard de la Liberté");
            logement3.setVille("Marseille");
            logement3.setCodePostal("13000");
            logement3.setTypeLogement("Studio");
            logement3.setDateCreation(LocalDateTime.now());
            logementRepository.save(logement3);

            System.out.println("✅ Logements de test créés !");

            // Créer des contrats de test
            Contrat contrat1 = new Contrat();
            contrat1.setUtilisateur(user1);
            contrat1.setLogement(logement1);
            contrat1.setDateDebut(LocalDate.of(2025, 1, 1));
            contrat1.setDateFin(LocalDate.of(2026, 12, 31));
            contrat1.setMontantLoyer(new BigDecimal("800.00"));
            contrat1.setMontantCaution(new BigDecimal("1600.00"));
            contrat1.setStatut("actif");
            contrat1.setDateCreation(LocalDateTime.now());
            contratRepository.save(contrat1);

            Contrat contrat2 = new Contrat();
            contrat2.setUtilisateur(user2);
            contrat2.setLogement(logement2);
            contrat2.setDateDebut(LocalDate.of(2025, 3, 15));
            contrat2.setDateFin(LocalDate.of(2027, 3, 14));
            contrat2.setMontantLoyer(new BigDecimal("1200.00"));
            contrat2.setMontantCaution(new BigDecimal("2400.00"));
            contrat2.setStatut("actif");
            contrat2.setDateCreation(LocalDateTime.now());
            contratRepository.save(contrat2);

            Contrat contrat3 = new Contrat();
            contrat3.setUtilisateur(user3);
            contrat3.setLogement(logement3);
            contrat3.setDateDebut(LocalDate.of(2024, 6, 1));
            contrat3.setDateFin(LocalDate.of(2026, 5, 31));
            contrat3.setMontantLoyer(new BigDecimal("600.00"));
            contrat3.setMontantCaution(new BigDecimal("1200.00"));
            contrat3.setStatut("actif");
            contrat3.setDateCreation(LocalDateTime.now());
            contratRepository.save(contrat3);

            System.out.println("✅ Contrats de test créés !");
            System.out.println("   3 contrats actifs avec des logements et utilisateurs");
        }
    }
}
