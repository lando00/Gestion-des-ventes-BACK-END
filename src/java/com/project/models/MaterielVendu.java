/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.models;

/**
 *
 * @author Orlando
 */
public class MaterielVendu {
    String numMateriel, design;
    Integer prixUnitaire, stock, montant;

    public MaterielVendu(String numMateriel, String design, Integer prixUnitaire, Integer stock, Integer montant) {
        this.numMateriel = numMateriel;
        this.design = design;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.montant = montant;
    }

    public String getNumMateriel() {
        return numMateriel;
    }

    public void setNumMateriel(String numMateriel) {
        this.numMateriel = numMateriel;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }
    
    
}
