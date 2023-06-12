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
import com.project.utils.Alerte;
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
    
    @GET
    @Path("filtrer_par_mois/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vente> filtrerVenteByMonth(@PathParam("month") Integer mois) {
        return venteDao.filterVenteByMonth(mois);
    }
    
    @GET
    @Path("filtrer_par_annee/{annee}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vente> filtrerVenteByYear(@PathParam("annee") Integer annee) {
        return venteDao.filterVenteByYear(annee);
    }
    
    @GET
    @Path("filtrer_par_date/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vente> filtrerVenteByDate(@PathParam("date1") String date1, @PathParam("date2") String date2) {
        return venteDao.filterVenteByDate(date1, date2);
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte addVente(@FormParam("numClient") String numClient, @FormParam("design") String design, 
            @FormParam("quantite") Integer quantite, @FormParam("stock") Integer stock){
        return venteDao.addVente(numClient, design, quantite, stock);
    }
    
    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte deleteVente(@PathParam("num") String numVente) {
        return venteDao.deleteVente(numVente);
    }
}

