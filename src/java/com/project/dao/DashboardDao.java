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

/**
 *
 * @author Orlando
 */
public class DashboardDao {

    public DashboardDao() {
    }
    
    public int getTotalClient(){
        int totalClient = 0;
        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(numClient) as totalClient from clients");
            while (result.next()) {
                totalClient = result.getInt("totalClient");
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return totalClient;
    }
    
    public int getTotalMateriel(){
        int totalMateriel = 0;
        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(numMateriel) as totalMateriel from materiels");
            while (result.next()) {
                totalMateriel = result.getInt("totalMateriel");
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return totalMateriel;
    }
    
    public int getTotalVente(){
        int totalVente = 0;
        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(numVente) as totalVente from ventes");
            while (result.next()) {
                totalVente = result.getInt("totalVente");
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return totalVente;
    }
    
    public int getTotalChiffreAffaire(){
        int totalCA = 0;
        try {
            
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT SUM(ventes.quantite*materiels.prixUnitaire) AS totalMontant "
                    + "FROM ventes, materiels WHERE materiels.numMateriel = ventes.numMateriel");
            while (result.next()) {
                totalCA = result.getInt("totalMontant");
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return totalCA;
    }
}
