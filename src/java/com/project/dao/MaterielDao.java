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
import com.project.models.MaterielVendu;
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
    
    
    public List<MaterielVendu> getMaterielVendu() {
        List<MaterielVendu> materielList = new ArrayList();

        try {

            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT materiels.numMateriel, materiels.design,"
                    + " materiels.prixUnitaire, materiels.stock, materiels.prixUnitaire*ventes.quantite as montant "
                    + "FROM `ventes`, `materiels` WHERE ventes.numMateriel = materiels.numMateriel;");
            while (result.next()) {
                String numMateriel = result.getString("numMateriel");
                String design = result.getString("design");
                Integer prixUnitaire = result.getInt("prixUnitaire");
                Integer stock = result.getInt("stock");
                Integer montant = result.getInt("montant");
                
                materielList.add(new MaterielVendu(numMateriel, design, prixUnitaire, stock, montant));
            }

            result.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return materielList;
    }
    
    public static Materiel getMaterielByDesign(String matDesign) {

        Materiel materiel = null;

        try {
            Connection conn = Db.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM materiels WHERE design='" + matDesign + "'");

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

    public Alerte addMateriel(String numMateriel, String design, Integer prixUnitaire, Integer stock) {
        Alerte alerte = new Alerte();
        boolean isFormCorrect = !numMateriel.isEmpty() && !design.isEmpty() && prixUnitaire != null && stock != null;
        
        try {
            Connection conn = Db.connect();
            Statement stmt = conn.createStatement();

            if (isFormCorrect) {
                int rs = stmt.executeUpdate("INSERT INTO materiels SET numMateriel='" + numMateriel + "', design='" + design + "', prixUnitaire='" + prixUnitaire + "', stock='" + stock + "'");
                if(rs == 1){
                    alerte.setType("success");
                    alerte.setMessage("Le materiel a été bien enregistré !");
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

    public Alerte updateMateriel(String id, String numMateriel, String design, Integer prixUnitaire, Integer stock) {
        Materiel materiel = this.getUniqueMateriel(id);
        Alerte alerte = new Alerte();

        if (materiel != null) {
            boolean isFormCorrect = !numMateriel.isEmpty() && !design.isEmpty() && prixUnitaire != null && stock != null;

            if (isFormCorrect) {
                try {
                    Connection conn = Db.connect();
                    Statement stmt = conn.createStatement();
                    int rs = stmt.executeUpdate("UPDATE materiels SET numMateriel='" + numMateriel + "', design='" + design + "', prixUnitaire='" + prixUnitaire + "', stock='" + stock + "' WHERE numMateriel='" + id + "'");
                    if(rs == 1){
                        alerte.setType("success");
                        alerte.setMessage("Modification réussi !");
                    }else{
                        alerte.setType("error");
                        alerte.setMessage("Erreur d'enregistrement, réessayer svp !");
                    }
                } catch (Exception e) {
                     alerte.setType("error");
                     alerte.setMessage("Erreur d'enregistrement, réessayer svp !");
                }
            } else {
                alerte.setType("warning");
                alerte.setMessage("Veuillez bien remplir tous les champs !");
            }

        } else {
             alerte.setType("warning");
             alerte.setMessage("Le materiel n'existe pas dans la liste !");
        }

        return alerte;
    }

    public Alerte deleteMateriel(String id) {
        Materiel materiel = this.getUniqueMateriel(id);
        Alerte alerte = new Alerte();

        if (materiel != null) {
            try {
                Connection conn = Db.connect();
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate("DELETE FROM materiels WHERE numMateriel='" + id + "'");
                if(rs == 1){
                    alerte.setType("success");
                    alerte.setMessage("Le materiel a été bien supprimé !");
                }else{
                    alerte.setType("error");
                    alerte.setMessage("Erreur, veuillez réessayer !");
                }
            } catch (Exception e) {
                alerte.setType("error");
                alerte.setMessage("Erreur, veuillez réessayer !");
            }

        } else {
             alerte.setType("warning");
             alerte.setMessage("Le materiel n'existe pas dans la liste !");
        }

        return alerte;
    }

}

