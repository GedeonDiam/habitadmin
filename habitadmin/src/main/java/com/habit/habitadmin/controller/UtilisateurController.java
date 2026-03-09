package com.habit.habitadmin.controller;

import com.habit.habitadmin.model.Utilisateur;
import com.habit.habitadmin.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/habitago/users")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Afficher la page de gestion des utilisateurs
    @GetMapping
    public String showUsersPage(Model model) {
        List<Utilisateur> users = utilisateurService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("totalUsers", users.size());
        return "app/habitago/users";
    }

    // API REST - Récupérer tous les utilisateurs
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        return ResponseEntity.ok(utilisateurService.getAllUsers());
    }

    // API REST - Ajouter un utilisateur
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur savedUser = utilisateurService.saveUser(utilisateur);
        return ResponseEntity.ok(savedUser);
    }

    // API REST - Récupérer un utilisateur par ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
        return utilisateurService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API REST - Mettre à jour un utilisateur
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUser = utilisateurService.updateUser(id, utilisateur);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // API REST - Supprimer un utilisateur
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}