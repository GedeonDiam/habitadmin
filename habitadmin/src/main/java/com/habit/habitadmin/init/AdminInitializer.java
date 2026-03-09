package com.habit.habitadmin.init;

import com.habit.habitadmin.model.Admin;
import com.habit.habitadmin.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Récupérer tous les admins
        List<Admin> admins = adminRepository.findAll();
        
        // Vérifier et hasher les mots de passe en clair
        for (Admin admin : admins) {
            String password = admin.getMotDePasse();
            
            // Vérifier s'il n'est pas déjà hashé (les mots de passe BCrypt commencent par $2a$, $2b$, $2x$, $2y$)
            if (password != null && !password.startsWith("$2a$") && !password.startsWith("$2b$") && 
                !password.startsWith("$2x$") && !password.startsWith("$2y$")) {
                
                // Hasher le mot de passe
                String hashedPassword = passwordEncoder.encode(password);
                admin.setMotDePasse(hashedPassword);
                adminRepository.save(admin);
                
                System.out.println("✅ Mot de passe de l'admin " + admin.getEmail() + " hashé avec succès");
            }
        }
        
        System.out.println("✅ Initialisation des admins terminée");
    }
}
