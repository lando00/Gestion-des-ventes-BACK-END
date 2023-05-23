/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

/**
 *
 * @author Orlando
 */
import com.project.dao.VenteDao;
import com.project.models.Vente;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Orlando
 */
@Path("/vente")
public class VenteService {

    VenteDao venteDao = new VenteDao();

    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vente> allVente() {
        return venteDao.getAllVente();
    }
    
    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vente uniqueVente(@PathParam("num") String numVente) {
        return venteDao.getUniqueVente(numVente);
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addVente(@FormParam("numClient") String numClient, @FormParam("design") String design, 
            @FormParam("quantite") Integer quantite, @FormParam("date") String date){
        return venteDao.addVente(numClient, design, quantite, date);
    }
    
    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteVent(@PathParam("num") String numVente) {
        return venteDao.deleteVente(numVente);
    }
}

