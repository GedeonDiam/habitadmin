package com.habit.habitadmin.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consommation")
public class Consommation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "logement_id", nullable = false)
    private Logement logement;

    private BigDecimal valeur;
    private String unite;

    @Column(name = "periode_debut")
    private LocalDate periodeDebut;

    @Column(name = "periode_fin")
    private LocalDate periodeFin;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    private String type;
    private Boolean validee;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Logement getLogement() { return logement; }
    public void setLogement(Logement logement) { this.logement = logement; }

    public BigDecimal getValeur() { return valeur; }
    public void setValeur(BigDecimal valeur) { this.valeur = valeur; }

    public String getUnite() { return unite; }
    public void setUnite(String unite) { this.unite = unite; }

    public LocalDate getPeriodeDebut() { return periodeDebut; }
    public void setPeriodeDebut(LocalDate periodeDebut) { this.periodeDebut = periodeDebut; }

    public LocalDate getPeriodeFin() { return periodeFin; }
    public void setPeriodeFin(LocalDate periodeFin) { this.periodeFin = periodeFin; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getValidee() { return validee; }
    public void setValidee(Boolean validee) { this.validee = validee; }
}