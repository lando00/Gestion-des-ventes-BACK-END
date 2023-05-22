/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.dao;

import com.project.bdd.Db;
import com.project.models.Client;
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

    public Client getUniqueClient(String num) {

        Client client = null;

        try {
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM clients WHERE numClient='" + num + "'");

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
    
    public String addClient(String newNumClient, String newNom, String newPrenom, String newTel) {
        String message;
        String  numClient = newNumClient;
        String  nom = newNom;
        String  prenom = newPrenom;
        String  tel = newTel; 
        boolean isFormCorrect = numClient != null && nom != null && prenom != null && tel != null;
        
        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();
            
            if(isFormCorrect){ 
                int rs = stmt.executeUpdate("INSERT INTO clients SET numClient='" + numClient + "', nom='" + nom + "', prenom='" + prenom + "', telephone='" + tel + "'");
                message = rs == 1 ? "Le client a été bien enregistré !" : "Erreur d'enregistrement, réessayer svp !";
            }else{
                message = "Veuillez bien remplir tous les champs !";
            }
            
        } catch (Exception e) {
            message = "Erreur d'enregistrement, réessayer svp !";
        }
        
       return message; 
    }

    public String updateClient(String num, String numClient, String nom, String prenom, String tel) {
        Client client = this.getUniqueClient(num);
        String message;
        
        if(client != null){
            boolean isFormCorrect = numClient != null && nom != null && prenom != null && tel != null;
            
            if(isFormCorrect){
                try {
                    Connection conn = Db.connect();
                    Statement stmt = conn.createStatement();
                    int rs = stmt.executeUpdate("UPDATE clients SET numClient='" + numClient + "', nom='" + nom + "', prenom='" + prenom + "', telephone='" + tel + "' WHERE numClient='" + num + "'");
                    message = rs == 1 ? "Modification réussi !" : "Erreur de modification";
                } catch (Exception e) {
                    message = "Erreur de modification";
                }
            }else{
                message = "Veuillez bien remplir tous les champs !";
            }
            
        }else{
            message = "Le client n'existe pas dans la liste";
        }
        
        
       return message; 
    }
    
    public String deleteClient(String num) {
        Client client = this.getUniqueClient(num);
        String message;
        
        if(client != null){
            try {
               Connection conn = Db.connect();
               Statement stmt = conn.createStatement();
               int rs = stmt.executeUpdate("DELETE FROM clients WHERE numClient='"+ num +"'");
               message = rs == 1 ? "Le client a été bien supprimé !" : "Erreur, veuillez réessayer !";
            } catch (Exception e) {
                message = "Erreur, veuillez réessayer !";
            }
            
        }else{
            message = "Le client n'existe pas dans la liste";
        }
        
        
       return message; 
    }

}
