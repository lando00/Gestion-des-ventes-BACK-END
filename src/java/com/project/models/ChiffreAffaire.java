/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.models;

/**
 *
 * @author Orlando
 */
public class ChiffreAffaire {
    String numClient, nom, prenom, annee;
    Integer montant;

    public ChiffreAffaire(String numClient, String nom, String prenom, Integer montant, String annee) {
        this.numClient = numClient;
        this.nom = nom;
        this.prenom = prenom;
        this.montant = montant;
        this.annee = annee;
    }

    public String getNumClient() {
        return numClient;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
    
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }
    
    
}
