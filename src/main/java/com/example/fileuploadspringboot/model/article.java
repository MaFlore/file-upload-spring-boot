package com.example.fileuploadspringboot.model;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;

    @Column(name = "image")
    private String image;

    public article() {
    }

    public article(String designation, String description, double prixUnitaire, String image) {
        this.designation = designation;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.image = image;
    }

    public article(Long id, String designation, String description, double prixUnitaire, String image) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
