/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.dao;

import com.project.bdd.Db;
import com.project.models.ChiffreAffaire;
import com.project.models.Client;
import com.project.models.InfoClient;
import com.project.models.Vente;
import com.project.utils.Alerte;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Orlando
 */
public class ClientDao {

    public ClientDao() {
    }

    public List<Client> getAllClient() {
        List<Client> clientList = new ArrayList();

        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from clients");
            while (result.next()) {
                String numClient = result.getString(1);
                String nom = result.getString(2);
                String prenom = result.getString(3);
                String tel = result.getString(4);

                clientList.add(new Client(numClient, nom, prenom, tel));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return clientList;
    }
    
    public List<ChiffreAffaire> getChiffreAffaire() {
        List<ChiffreAffaire> clientList = new ArrayList();

        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT clients.numClient, clients.nom, clients.prenom, "
                    + "materiels.prixUnitaire*ventes.quantite as montant, YEAR(ventes.date) as annee FROM `ventes`, `materiels`, `clients` "
                    + "WHERE ventes.numMateriel = materiels.numMateriel AND clients.numClient = ventes.numClient;");
            while (result.next()) {
                String numClient = result.getString("numClient");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                Integer montant = result.getInt("montant");
                String annee = result.getString("annee");

                clientList.add(new ChiffreAffaire(numClient, nom, prenom, montant, annee));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return clientList;
    }
    
    public Client getUniqueClient(String id) {

        Client client = null;

        try {
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM clients WHERE numClient='" + id + "'");

            if (result.first()) {
                String numClient = result.getString(1);
                String nom = result.getString(2);
                String prenom = result.getString(3);
                String tel = result.getString(4);

                client = new Client(numClient, nom, prenom, tel);

                return client;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return client;
    }

    public Alerte addClient(String numClient, String nom, String prenom, String tel) {
        
        Alerte alerte = new Alerte();
    
        boolean isFormCorrect = !numClient.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !tel.isEmpty();

        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();
            if (isFormCorrect) {
                int rs = stmt.executeUpdate("INSERT INTO clients SET numClient='" + numClient + "', nom='" + nom + "', prenom='" + prenom + "', telephone='" + tel + "'");
                if(rs == 1){
                    alerte.setType("success");
                    alerte.setMessage("Le client a été bien enregistré !");
                }else{
                    alerte.setType("error");
                    alerte.setMessage("Erreur d'enregistrement, réessayer svp !");
                }
            } else {
                alerte.setType("warning");
                alerte.setMessage("Veuillez bien remplir tous les champs !");
            }

        } catch (Exception e) {
             alerte.setType("error");
             alerte.setMessage("Erreur d'enregistrement, réessayer svp !");
        }
        
        return alerte;
    }

    public Alerte updateClient(String id, String numClient, String nom, String prenom, String tel) {
        Client client = this.getUniqueClient(id);
        Alerte alerte = new Alerte();

        if (client != null) {
            boolean isFormCorrect = !numClient.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !tel.isEmpty();

            if (isFormCorrect) {
                try {
                    Connection conn = Db.connect();
                    Statement stmt = conn.createStatement();
                    int rs = stmt.executeUpdate("UPDATE clients SET numClient='" + numClient + "', nom='" + nom + "', prenom='" + prenom + "', telephone='" + tel + "' WHERE numClient='" + id + "'");
                    if(rs == 1){
                        alerte.setType("success");
                        alerte.setMessage("Modification reussi !");
                    }else{
                        alerte.setType("error");
                        alerte.setMessage("Erreur de modification, réessayer svp !");
                    }
                } catch (Exception e) {
                     alerte.setType("error");
                     alerte.setMessage("Erreur de modification, réessayer svp !");
                }
            } else {
                 alerte.setType("warning");
                 alerte.setMessage("Veuillez bien remplir tous les champs !");
            }

        } else {
            alerte.setType("warning");
            alerte.setMessage("Le client n'existte pas dans la liste !");
        }

        return alerte;
    }

    public Alerte deleteClient(String id) {
        Client client = this.getUniqueClient(id);
        Alerte alerte = new Alerte();

        if (client != null) {
            try {
                Connection conn = Db.connect();
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate("DELETE FROM clients WHERE numClient='" + id + "'");
                if(rs == 1){
                    alerte.setType("success");
                    alerte.setMessage("Le client a été bien supprimé !");
                }else{
                    alerte.setType("error");
                    alerte.setMessage("Erreur, veuillez réessayer !");
                }
            } catch (Exception e) {
                alerte.setType("error");
                alerte.setMessage("Erreur, veuillez réessayer !");
            }

        } else {
             alerte.setType("error");
             alerte.setMessage("Le client n'existe pas !");
        }

        return alerte;
    }
    
    public List<InfoClient> getInfoClient(String id) {
        List<InfoClient> infoClientTab = new ArrayList();

        try {

            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT clients.numClient, clients.nom, clients.prenom, "
                    + "materiels.numMateriel, materiels.design, materiels.prixUnitaire, ventes.quantite, "
                    + "materiels.prixUnitaire*ventes.quantite as montant, ventes.date FROM `ventes`, `clients`, `materiels` "
                    + " WHERE ventes.numClient='"+ id +"' AND ventes.numClient = clients.numClient "
                    + "AND ventes.numMateriel = materiels.numMateriel;");
            while (result.next()) {
                
                String numClient = result.getString("numClient");
                String nomClient = result.getString("nom") +" "+ result.getString("prenom");
                String numMateriel = result.getString("numMateriel");
                String design = result.getString("design");
                Integer prixUnitaire = result.getInt("prixUnitaire");
                Integer quantite = result.getInt("quantite");
                Integer montant = result.getInt("montant");
                String date = result.getString("date");
                

                infoClientTab.add(new InfoClient(numClient, nomClient, numMateriel, design, prixUnitaire, quantite, montant, date));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return infoClientTab;
    }
}
