/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.dao;

import com.project.bdd.Db;
import com.project.utils.Alerte;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Orlando
 */
public class LoginDao {

    public LoginDao() {
    }
    
    public Alerte login(String nomUtilisateur, String motDePasse){
        Alerte alerte = new Alerte();
        boolean isFormCorrect = !nomUtilisateur.isEmpty() && !motDePasse.isEmpty();
        if(isFormCorrect){
            try {
                Connection conn = Db.connect();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM login WHERE nomUtilisateur='" + nomUtilisateur + "' "
                        + "AND motDePasse='"+ motDePasse +"'");

                if (result.first()) {
                    alerte.setType("success");
                    alerte.setMessage("Connexion reussi !");

                }else{
                    alerte.setType("error");
                    alerte.setMessage("Nom d'utilisateur ou mot de passe incorrect");
                }     
            
            } catch (Exception e) {
                alerte.setType("error");
                alerte.setMessage("Erreur, veillez réessayer !");
            }
        }else{
            alerte.setType("warning");
            alerte.setMessage("Veuillez bien remplir tous les champs !");
        }
        
        
        return alerte;
    }
}
