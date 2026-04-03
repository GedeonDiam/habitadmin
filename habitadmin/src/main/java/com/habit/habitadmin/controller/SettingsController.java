package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Admin;
import com.habit.habitadmin.repository.AdminRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public SettingsController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Afficher la page des paramètres
    @GetMapping
    public String showSettings(Authentication authentication, Model model) {
        String email = authentication.getName();
        Admin admin = adminRepository.findByEmail(email);
        
        if (admin == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("admin", admin);
        return "app/habitago/settings";
    }

    // Mettre à jour le profil
    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam String nom,
            @RequestParam String prenom,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        
        String email = authentication.getName();
        Admin admin = adminRepository.findByEmail(email);
        
        if (admin != null) {
            admin.setNom(nom);
            admin.setPrenom(prenom);
            adminRepository.save(admin);
            redirectAttributes.addFlashAttribute("successMessage", "✅ Profil mis à jour avec succès !");
        }
        
        return "redirect:/settings";
    }

    // Changer le mot de passe
    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        
        String email = authentication.getName();
        Admin admin = adminRepository.findByEmail(email);
        
        if (admin == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Administrateur non trouvé !");
            return "redirect:/settings";
        }
        
        // Vérifier le mot de passe actuel
        if (!passwordEncoder.matches(currentPassword, admin.getMotDePasse())) {
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Mot de passe actuel incorrect !");
            return "redirect:/settings";
        }
        
        // Vérifier que les nouveaux mots de passe correspondent
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Les nouveaux mots de passe ne correspondent pas !");
            return "redirect:/settings";
        }
        
        // Vérifier la longueur
        if (newPassword.length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Le nouveau mot de passe doit contenir au moins 6 caractères !");
            return "redirect:/settings";
        }
        
        // Mettre à jour le mot de passe
        admin.setMotDePasse(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Mot de passe modifié avec succès !");
        
        return "redirect:/settings";
    }
}
