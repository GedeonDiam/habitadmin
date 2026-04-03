package com.habit.habitadmin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logement")
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresse;
    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "type_logement")
    private String typeLogement;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // @PrePersist - Pour initialiser la date de création
    @PrePersist
    public void createDate() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    // @PreUpdate - Pour mettre à jour la date de modification
    @PreUpdate
    public void updateDate() {
        this.dateModification = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getTypeLogement() { return typeLogement; }
    public void setTypeLogement(String typeLogement) { this.typeLogement = typeLogement; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
}