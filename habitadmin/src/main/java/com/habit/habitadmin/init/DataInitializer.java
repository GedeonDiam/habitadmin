package com.habit.habitadmin.init;

import com.habit.habitadmin.model.Utilisateur;
import com.habit.habitadmin.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
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
    }
}
