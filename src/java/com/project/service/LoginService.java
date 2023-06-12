/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

import com.project.dao.LoginDao;
import com.project.utils.Alerte;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


/**
 *
 * @author Orlando
 */
@Path("/authentification")
public class LoginService {
    LoginDao loginDao = new LoginDao();
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte login(@FormParam("nomUtilisateur") String nomUtilisateur, @FormParam("motDePasse") String motDePasse) {
        return loginDao.login(nomUtilisateur, motDePasse);
    }
}
