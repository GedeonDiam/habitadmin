package com.habit.habitadmin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logement")
public class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String ville;

    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(name = "type_logement", nullable = false)
    private String typeLogement;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    public Logement() {
        this.dateCreation = LocalDateTime.now();
    }

    public Logement(String adresse, String ville, String codePostal, String typeLogement) {
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.typeLogement = typeLogement;
        this.dateCreation = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(String typeLogement) {
        this.typeLogement = typeLogement;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Logement{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", typeLogement='" + typeLogement + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
