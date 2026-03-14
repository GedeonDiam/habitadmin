package com.habit.habitadmin.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "logement_id", nullable = false)
    private Logement logement;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "montant_loyer")
    private BigDecimal montantLoyer;

    @Column(name = "montant_caution")
    private BigDecimal montantCaution;

    private String statut;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Logement getLogement() { return logement; }
    public void setLogement(Logement logement) { this.logement = logement; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public BigDecimal getMontantLoyer() { return montantLoyer; }
    public void setMontantLoyer(BigDecimal montantLoyer) { this.montantLoyer = montantLoyer; }

    public BigDecimal getMontantCaution() { return montantCaution; }
    public void setMontantCaution(BigDecimal montantCaution) { this.montantCaution = montantCaution; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}