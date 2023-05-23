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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author Orlando
 */
public class MaterielDao {

    public MaterielDao() {
    }

    public List<Materiel> getAllMateriel() {
        List<Materiel> materielList = new ArrayList();

        try {

            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from materiels");
            while (result.next()) {
                String numMateriel = result.getString(1);
                String design = result.getString(2);
                int prixUnitaire = result.getInt(3);
                int stock = result.getInt(4);

                materielList.add(new Materiel(numMateriel, design, prixUnitaire, stock));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return materielList;
    }

    public Materiel getUniqueMateriel(String id) {

        Materiel materiel = null;

        try {
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM materiels WHERE numMateriel='" + id + "'");

            if (result.first()) {
                String numMateriel = result.getString(1);
                String design = result.getString(2);
                int prixUnitaire = result.getInt(3);
                int stock = result.getInt(4);

                materiel = new Materiel(numMateriel, design, prixUnitaire, stock);

                return materiel;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return materiel;
    }

    public String addMateriel(String newNumMateriel, String newDesign, Integer newPrixUnitaire, Integer newStock) {
        String message;
        String numMateriel = newNumMateriel;
        String design = newDesign;
        Integer prixUnitaire = newPrixUnitaire;
        Integer stock = newStock;
        boolean isFormCorrect = numMateriel != null && design != null && prixUnitaire != null && stock != null;
        
        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();

            if (isFormCorrect) {
                int rs = stmt.executeUpdate("INSERT INTO materiels SET numMateriel='" + numMateriel + "', design='" + design + "', prixUnitaire='" + prixUnitaire + "', stock='" + stock + "'");
                message = rs == 1 ? "Le materiel a été bien enregistré !" : "Erreur d'enregistrement, réessayer svp !";
            } else {
                message = "Veuillez bien remplir tous les champs !";
            }

        } catch (Exception e) {
            message = "Erreur d'enregistrement, réessayer svp !";
        }

        return message;
    }

    public String updateMateriel(String id, String numMateriel, String design, Integer prixUnitaire, Integer stock) {
        Materiel materiel = this.getUniqueMateriel(id);
        String message;

        if (materiel != null) {
            boolean isFormCorrect = numMateriel != null && design != null && prixUnitaire != null && stock != null;

            if (isFormCorrect) {
                try {
                    Connection conn = Db.connect();
                    Statement stmt = conn.createStatement();
                    int rs = stmt.executeUpdate("UPDATE materiels SET numMateriel='" + numMateriel + "', design='" + design + "', prixUnitaire='" + prixUnitaire + "', stock='" + stock + "' WHERE numMateriel='" + id + "'");
                    message = rs == 1 ? "Modification réussi !" : "Erreur de modification";
                } catch (Exception e) {
                    message = "Erreur de modification";
                }
            } else {
                message = "Veuillez bien remplir tous les champs !";
            }

        } else {
            message = "Le client n'existe pas dans la liste";
        }

        return message;
    }

    public String deleteMateriel(String id) {
        Materiel materiel = this.getUniqueMateriel(id);
        String message;

        if (materiel != null) {
            try {
                Connection conn = Db.connect();
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate("DELETE FROM materiels WHERE numMateriel='" + id + "'");
                message = rs == 1 ? "Le materiel a été bien supprimé !" : "Erreur, veuillez réessayer !";
            } catch (Exception e) {
                message = "Erreur, veuillez réessayer !";
            }

        } else {
            message = "Le materiel n'existe pas dans la liste";
        }

        return message;
    }

}

