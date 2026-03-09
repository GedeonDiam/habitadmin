package com.habit.habitadmin.service;

import com.habit.habitadmin.model.Utilisateur;
import com.habit.habitadmin.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<Utilisateur> getUserById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Sauvegarder un utilisateur (création)
    public Utilisateur saveUser(Utilisateur utilisateur) {
        utilisateur.setDateCreation(LocalDateTime.now());
        utilisateur.setDateModification(LocalDateTime.now());
        return utilisateurRepository.save(utilisateur);
    }

    // Mettre à jour un utilisateur
    public Utilisateur updateUser(Long id, Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        
        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();
            
            if (utilisateurDetails.getEmail() != null) {
                user.setEmail(utilisateurDetails.getEmail());
            }
            if (utilisateurDetails.getNom() != null) {
                user.setNom(utilisateurDetails.getNom());
            }
            if (utilisateurDetails.getPrenom() != null) {
                user.setPrenom(utilisateurDetails.getPrenom());
            }
            if (utilisateurDetails.getRole() != null) {
                user.setRole(utilisateurDetails.getRole());
            }
            if (utilisateurDetails.getMotDePasse() != null && !utilisateurDetails.getMotDePasse().isEmpty()) {
                user.setMotDePasse(utilisateurDetails.getMotDePasse());
            }
            
            user.setDateModification(LocalDateTime.now());
            return utilisateurRepository.save(user);
        }
        
        return null;
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }
}