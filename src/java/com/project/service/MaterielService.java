/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

/**
 *
 * @author Orlando
 */
import com.project.dao.MaterielDao;
import com.project.models.Materiel;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Orlando
 */
@Path("/materiel")
public class MaterielService {

    MaterielDao materielDao = new MaterielDao();

    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materiel> allMateriel() {
        return materielDao.getAllMateriel();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Materiel uniqueMateriel(@PathParam("id") String numMateriel) {
        return materielDao.getUniqueMateriel(numMateriel);
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addMateriel(@FormParam("numMateriel") String numMateriel, @FormParam("design") String design, @FormParam("prixUnitaire") Integer prixUnitaire, @FormParam("stock") Integer stock){
        return materielDao.addMateriel(numMateriel, design, prixUnitaire, stock);
    }
    
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateMateriel(@PathParam("id") String id ,@FormParam("numMateriel") String numMateriel, @FormParam("design") String design, @FormParam("prixUnitaire") Integer prixUnitaire, @FormParam("stock") Integer stock){
        return materielDao.updateMateriel(id, numMateriel, design, prixUnitaire, stock);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteMateriel(@PathParam("id") String id) {
        return materielDao.deleteMateriel(id);
    }
}

