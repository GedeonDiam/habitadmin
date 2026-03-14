package com.habit.habitadmin.init;

import com.habit.habitadmin.model.*;
import com.habit.habitadmin.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class TestDataInitializer implements CommandLineRunner {

    private final LogementRepository logementRepository;
    private final ConsommationRepository consommationRepository;
    private final ContratRepository contratRepository;
    private final UtilisateurRepository utilisateurRepository;

    public TestDataInitializer(LogementRepository logementRepository,
                              ConsommationRepository consommationRepository,
                              ContratRepository contratRepository,
                              UtilisateurRepository utilisateurRepository) {
        this.logementRepository = logementRepository;
        this.consommationRepository = consommationRepository;
        this.contratRepository = contratRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer des données de test seulement si la base est vide
        if (logementRepository.count() == 0) {
            createTestLogements();
        }
        if (utilisateurRepository.count() == 0) {
            createTestUtilisateurs();
        }
        if (contratRepository.count() == 0) {
            createTestContrats();
        }
        if (consommationRepository.count() == 0) {
            createTestConsommations();
        }
    }

    private void createTestLogements() {
        String[] types = {"APPARTEMENT", "MAISON", "STUDIO", "COLOCATION"};
        String[] villes = {"Paris", "Lyon", "Marseille", "Toulouse", "Bordeaux", "Lille"};
        
        for (int i = 1; i <= 20; i++) {
            Logement logement = new Logement();
            logement.setAdresse(i + " Rue de Test");
            logement.setVille(villes[new Random().nextInt(villes.length)]);
            logement.setCodePostal(String.valueOf(75000 + new Random().nextInt(1000)));
            logement.setTypeLogement(types[new Random().nextInt(types.length)]);
            logement.setDateCreation(LocalDateTime.now().minusDays(new Random().nextInt(365)));
            logement.setDateModification(LocalDateTime.now());
            logementRepository.save(logement);
        }
        System.out.println("✅ 20 logements de test créés");
    }

    private void createTestUtilisateurs() {
        String[] prenoms = {"Jean", "Marie", "Pierre", "Sophie", "Thomas", "Julie"};
        String[] noms = {"Dupont", "Martin", "Bernard", "Petit", "Robert", "Richard"};
        String[] roles = {"locataire", "proprietaire", "admin"};
        
        for (int i = 1; i <= 30; i++) {
            Utilisateur user = new Utilisateur();
            user.setEmail("user" + i + "@test.com");
            user.setMotDePasse("$2a$10$" + new Random().nextInt(1000000)); // Hash factice
            user.setPrenom(prenoms[new Random().nextInt(prenoms.length)]);
            user.setNom(noms[new Random().nextInt(noms.length)]);
            user.setRole(roles[new Random().nextInt(roles.length)]);
            user.setDateCreation(LocalDateTime.now().minusDays(new Random().nextInt(365)));
            user.setDateModification(LocalDateTime.now());
            utilisateurRepository.save(user);
        }
        System.out.println("✅ 30 utilisateurs de test créés");
    }

    private void createTestContrats() {
        String[] statuts = {"ACTIF", "RESILIE", "EN_ATTENTE"};
        Random random = new Random();
        
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        List<Logement> logements = logementRepository.findAll();
        
        if (utilisateurs.isEmpty() || logements.isEmpty()) {
            System.out.println("⚠️ Pas assez de données pour créer des contrats");
            return;
        }
        
        for (int i = 1; i <= 25; i++) {
            Contrat contrat = new Contrat();
            contrat.setUtilisateur(utilisateurs.get(random.nextInt(utilisateurs.size())));
            contrat.setLogement(logements.get(random.nextInt(logements.size())));
            contrat.setDateDebut(LocalDate.now().minusMonths(random.nextInt(12)));
            contrat.setDateFin(LocalDate.now().plusMonths(random.nextInt(24)));
            contrat.setMontantLoyer(BigDecimal.valueOf(500 + random.nextInt(1000)));
            contrat.setMontantCaution(BigDecimal.valueOf(1000 + random.nextInt(1000)));
            contrat.setStatut(statuts[random.nextInt(statuts.length)]);
            contrat.setDateCreation(LocalDateTime.now().minusDays(random.nextInt(365)));
            contratRepository.save(contrat);
        }
        System.out.println("✅ 25 contrats de test créés");
    }

    private void createTestConsommations() {
        String[] types = {"ELECTRICITE", "EAU", "GAZ"};
        String[] unites = {"kWh", "m³", "kWh"};
        Random random = new Random();
        
        List<Logement> logements = logementRepository.findAll();
        
        if (logements.isEmpty()) {
            System.out.println("⚠️ Pas assez de logements pour créer des consommations");
            return;
        }
        
        for (int i = 1; i <= 100; i++) {
            Consommation conso = new Consommation();
            conso.setLogement(logements.get(random.nextInt(logements.size())));
            conso.setValeur(BigDecimal.valueOf(50 + random.nextInt(500)));
            int typeIndex = random.nextInt(types.length);
            conso.setType(types[typeIndex]);
            conso.setUnite(unites[typeIndex]);
            conso.setPeriodeDebut(LocalDate.now().minusMonths(random.nextInt(12)));
            conso.setPeriodeFin(conso.getPeriodeDebut().plusMonths(1));
            conso.setValidee(random.nextBoolean());
            conso.setDateCreation(LocalDateTime.now().minusDays(random.nextInt(365)));
            consommationRepository.save(conso);
        }
        System.out.println("✅ 100 consommations de test créées");
    }
}