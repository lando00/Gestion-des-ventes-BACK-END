/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.models;

/**
 *
 * @author Orlando
 */
public class Vente {

    String numVente, nomClient, materiel, date;
    Integer prixUnitaire, quantite, montant;

    public Vente() {
    }

    public String getNumVente() {
        return numVente;
    }

    public void setNumVente(String numVente) {
        this.numVente = numVente;
    }

    public Vente(String numVente, String nomClient, String materiel, String date, Integer prixUnitaire, Integer quantite, Integer montant) {
        this.numVente = numVente;
        this.nomClient = nomClient;
        this.materiel = materiel;
        this.date = date;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.montant = montant;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

}
