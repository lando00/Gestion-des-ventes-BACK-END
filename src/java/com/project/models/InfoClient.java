/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.models;

/**
 *
 * @author Orlando
 */
public class InfoClient {
    
    private String numClient, nomClient, numMateriel, design, date;
    private Integer quantite, montant, prixUnitaire;

    public InfoClient(String numClient, String nomClient, String numMateriel, String design, Integer prixUnitaire, Integer quantite, Integer montant, String date) {
        this.numClient = numClient;
        this.nomClient = nomClient;
        this.numMateriel = numMateriel;
        this.design = design;
        this.quantite = quantite;
        this.montant = montant;
        this.prixUnitaire = prixUnitaire;
        this.date = date;
    }

    public String getNumClient() {
        return numClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getNumMateriel() {
        return numMateriel;
    }

    public String getDesign() {
        return design;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Integer getMontant() {
        return montant;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }
    
    
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setNumMateriel(String numMateriel) {
        this.numMateriel = numMateriel;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
