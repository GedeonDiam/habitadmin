package com.habit.habitadmin.security;

import com.habit.habitadmin.model.Utilisateur;
import com.habit.habitadmin.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Charger l'utilisateur par email depuis la base de données
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);
        
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé: " + username);
        }

        // Convertir le rôle en GrantedAuthority
        List<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().toUpperCase())
        );

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
