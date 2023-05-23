/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.models;

/**
 *
 * @author Orlando
 */
public class Materiel {
    String numMateriel, design;
    int prixUnitaire, stock;
    
    public Materiel(){
    }

    public Materiel(String numMateriel, String design, int prixUnitaire, int stock) {
        this.numMateriel = numMateriel;
        this.design = design;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
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

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
