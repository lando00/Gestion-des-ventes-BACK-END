/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.dao;

/**
 *
 * @author Orlando
 */
import com.project.bdd.Db;
import com.project.models.Materiel;
import com.project.models.Vente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.project.models.Vente;
import com.project.utils.Alerte;

/**
 *
 * @author Orlando
 */
public class VenteDao {

    public VenteDao() {
    }

    public List<Vente> getAllVente() {
        List<Vente> venteList = new ArrayList();

        try {

            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT nom, prenom, design, prixUnitaire, quantite, "
                    + "prixUnitaire*quantite as montant, date, numVente FROM `ventes`, `clients`, `materiels` "
                    + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel "
                    + "ORDER BY numVente");
            while (result.next()) {
                String nomClient = result.getString(2);
                String materiel = result.getString(3);
                int prixUnitaire = result.getInt(4);
                int quantite = result.getInt(5);
                int montant = result.getInt(6);
                String date = result.getString(7);
                String numVente = result.getString(8);

                venteList.add(new Vente(numVente, nomClient, materiel, date,prixUnitaire, quantite, montant));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return venteList;
    }
    
    public Vente getUniqueVente(String id) {

        Vente venteSelected = null;

        try {
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT nom, prenom, design, prixUnitaire, quantite, "
                    + "prixUnitaire*quantite as montant, date, numVente FROM `ventes`, `clients`, `materiels` "
                    + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel "
                    + "AND numVente = '"+ id +"'");
            if (result.first()) {
                String nomClient = result.getString(2);
                String materiel = result.getString(3);
                int prixUnitaire = result.getInt(4);
                int quantite = result.getInt(5);
                int montant = result.getInt(6);
                String date = result.getString(7);
                String numVente = result.getString(8);

                venteSelected = new Vente(numVente, nomClient, materiel, date, prixUnitaire, quantite, montant);
               
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return venteSelected;
    }

    public Alerte addVente(String numClient, String design, Integer quantite, Integer stock) {
        Alerte alerte = new Alerte();
        Materiel materielSelected = MaterielDao.getMaterielByDesign(design);
        Integer newStock = stock - quantite;
        boolean isFormCorrect = !numClient.isEmpty() && !design.isEmpty() && quantite != null;

        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();

            if (isFormCorrect) {
                int rs = stmt.executeUpdate("INSERT INTO `ventes` (`numVente`, `numClient`, `numMateriel`, "
                        + "`quantite`, `date`) VALUES (NULL, '"+ numClient +"', "
                        + "'"+ materielSelected.getNumMateriel() +"', '"+ quantite +"', CURRENT_DATE())");              
                if(rs == 1){       
                    alerte.setType("success");
                    alerte.setMessage("La vente a été bien effectuée !");
                    stmt.executeUpdate("UPDATE materiels SET stock='" + newStock + "' WHERE numMateriel='" + materielSelected.getNumMateriel() + "'");

                }else{
                    alerte.setType("error");
                    alerte.setMessage("Erreur d'enregistrement, réessayer svp !");
                }
            } else {
                alerte.setType("warning");
                alerte.setMessage("Veuillez choisir un materiel !");
            }

        } catch (Exception e) {
            alerte.setType("error");
            alerte.setMessage("Erreur d'enregistrement2, réessayer svp !");
        }

        return alerte;
    }

    public Alerte deleteVente(String id) {
        Vente venteSelected = this.getUniqueVente(id);
        Materiel materielSelected = MaterielDao.getMaterielByDesign(venteSelected.getMateriel());
        Integer newStock = materielSelected.getStock() + venteSelected.getQuantite();
        Alerte alerte = new Alerte();
        
        if (venteSelected != null) {
            try {
                Connection conn = Db.connect();
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate("DELETE FROM ventes WHERE numVente='" + id + "'");
                if(rs == 1){
                    alerte.setType("success");
                    alerte.setMessage("La vente a été bien supprimée !");
                    stmt.executeUpdate("UPDATE materiels SET stock='" + newStock + "' WHERE numMateriel='" + materielSelected.getNumMateriel() + "'");

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
            alerte.setMessage("La vente n'existe pas dans la liste");
        }

        return alerte;
}   
        
         public List<Vente> filterVenteByMonth(Integer month) {
        
            List<Vente> venteList = new ArrayList();

            try {

                Connection conn = Db.connect();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT nom, prenom, design, prixUnitaire, quantite, "
                        + "prixUnitaire*quantite as montant, date, numVente FROM `ventes`, `clients`, `materiels` "
                        + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel "
                        + "AND MONTH(ventes.date) = '"+month+"' ORDER BY numVente");
                while (result.next()) {
                    String nomClient = result.getString(2);
                    String materiel = result.getString(3);
                    int prixUnitaire = result.getInt(4);
                    int quantite = result.getInt(5);
                    int montant = result.getInt(6);
                    String date = result.getString(7);
                    String numVente = result.getString(8);

                    venteList.add(new Vente(numVente, nomClient, materiel, date,prixUnitaire, quantite, montant));
                }

                result.close();
                statement.close();
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            return venteList;

           
        }
         
          public List<Vente> filterVenteByYear(Integer annee) {
        
            List<Vente> venteList = new ArrayList();

            try {

                Connection conn = Db.connect();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT nom, prenom, design, prixUnitaire, quantite, "
                        + "prixUnitaire*quantite as montant, date, numVente FROM `ventes`, `clients`, `materiels` "
                        + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel "
                        + "AND YEAR(ventes.date) = '"+annee+"' ORDER BY numVente");
                while (result.next()) {
                    String nomClient = result.getString(2);
                    String materiel = result.getString(3);
                    int prixUnitaire = result.getInt(4);
                    int quantite = result.getInt(5);
                    int montant = result.getInt(6);
                    String date = result.getString(7);
                    String numVente = result.getString(8);

                    venteList.add(new Vente(numVente, nomClient, materiel, date,prixUnitaire, quantite, montant));
                }

                result.close();
                statement.close();
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            return venteList;

           
        }
          
      
      public List<Vente> filterVenteByDate(String date1, String date2) {
        
            List<Vente> venteList = new ArrayList();

            try {

                Connection conn = Db.connect();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT nom, prenom, design, prixUnitaire, quantite, "
                        + "prixUnitaire*quantite as montant, date, numVente FROM `ventes`, `clients`, `materiels` "
                        + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel "
                        + "AND ventes.date BETWEEN '"+date1+"' AND '"+date2+"' ORDER BY numVente");
                while (result.next()) {
                    String nomClient = result.getString(2);
                    String materiel = result.getString(3);
                    int prixUnitaire = result.getInt(4);
                    int quantite = result.getInt(5);
                    int montant = result.getInt(6);
                    String date = result.getString(7);
                    String numVente = result.getString(8);

                    venteList.add(new Vente(numVente, nomClient, materiel, date,prixUnitaire, quantite, montant));
                }

                result.close();
                statement.close();
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            return venteList;

           
        }

}
