/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.entity;

/**
 *
 * @author Orlando
 */
public class Client {

    String numClient;
    String nom;
    String prenom;
    String tel;

    public Client() {
    }

    public Client(String numClient, String nom, String prenom, String tel) {
        this.numClient = numClient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    public String getNumClient() {
        return numClient;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
