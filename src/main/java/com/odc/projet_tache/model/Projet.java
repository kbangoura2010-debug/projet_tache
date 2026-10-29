package com.odc.projet_tache.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nom;

    private String description;

    private LocalDateTime dateDeCreation;

    @Column(nullable = false, length = 100)
    private String creePar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutProjet statut;

    private LocalDateTime dateDeFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priorite priorite;

    @Column(precision = 15, scale = 2)
    private BigDecimal budget;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getCreePar() {
        return creePar;
    }

    public void setCreePar(String creePar) {
        this.creePar = creePar;
    }

    public StatutProjet getStatut() {
        return statut;
    }

    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(LocalDateTime dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}