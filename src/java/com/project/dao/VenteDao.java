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
                    + "WHERE ventes.numClient = clients.numClient AND ventes.numMateriel = materiels.numMateriel");
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

                venteSelected = new Vente(numVente, nomClient, materiel, date,prixUnitaire, quantite, montant);
               
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return venteSelected;
    }

    public String addVente(String numClient, String design, Integer quantite, String date) {
        String message;
        Materiel materielSelected = MaterielDao.getMaterielByDesign(design);
        boolean isFormCorrect = numClient != null && design != null && quantite != null && date != null;

        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();

            if (isFormCorrect) {
                int rs = stmt.executeUpdate("INSERT INTO `ventes` (`numVente`, `numClient`, `numMateriel`, "
                        + "`quantite`, `date`) VALUES (NULL, '"+ numClient +"', "
                        + "'"+ materielSelected.getNumMateriel() +"', '"+ quantite +"', '"+ date +"')");              
                message = rs == 1 ? "La vente a été bien effectuée !" : "Erreur d'enregistrement, réessayer svp !";
            } else {
                message = "Veuillez bien remplir tous les champs !";
            }

        } catch (Exception e) {
            message = "Erreur d'enregistrement, réessayer svp !";
        }

        return message;
    }

    public String deleteVente(String id) {
        Vente venteSelected = this.getUniqueVente(id);
        String message;

        if (venteSelected != null) {
            try {
                Connection conn = Db.connect();
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate("DELETE FROM ventes WHERE numVente='" + id + "'");
                message = rs == 1 ? "La vente a été bien supprimé !" : "Erreur, veuillez réessayer !";
            } catch (Exception e) {
                message = "Erreur, veuillez réessayer !";
            }

        } else {
            message = "La vente n'existe pas dans la liste";
        }

        return message;
    }

}
