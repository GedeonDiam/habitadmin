package com.habit.habitadmin.security;

import com.habit.habitadmin.model.Admin;
import com.habit.habitadmin.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Charger l'administrateur par email depuis la base de données
        Admin admin = adminRepository.findByEmail(username);
        
        if (admin == null) {
            throw new UsernameNotFoundException("Admin non trouvé: " + username);
        }

        // Convertir le rôle en GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // Gérer le format du rôle (avec ou sans ROLE_)
        String roleName = admin.getRole();
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName.toUpperCase();
        }
        
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new CustomUserDetails(
                admin.getEmail(),
                admin.getMotDePasse(),
                authorities,
                admin.getPrenom(),
                admin.getNom(),
                admin.getActif()
        );
    }
}
