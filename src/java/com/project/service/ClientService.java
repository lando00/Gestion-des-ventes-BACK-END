/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

import com.project.dao.ClientDao;
import com.project.models.ChiffreAffaire;
import com.project.models.Client;
import com.project.models.InfoClient;
import com.project.utils.Alerte;
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
@Path("/client")
public class ClientService {

    ClientDao clientDao = new ClientDao();

    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> allClient() {
        return clientDao.getAllClient();
    }
    
    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Client uniqueClient(@PathParam("num") String numClient) {
        return clientDao.getUniqueClient(numClient);
    }
    
    @GET
    @Path("/info_client/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InfoClient> infoClient(@PathParam("num") String numClient) {
        return clientDao.getInfoClient(numClient);
    }
    
    @GET
    @Path("/chiffre_affaire")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChiffreAffaire> chiffreAffaire() {
        return clientDao.getChiffreAffaire();
    }
    
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte addClient(@FormParam("numClient") String numClient, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("tel") String tel){
        return clientDao.addClient(numClient, nom, prenom, tel);
    }
    
    @PUT  
    @Path("/update/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte updateClient(@PathParam("num") String num ,@FormParam("numClient") String numClient, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("tel") String tel){
        return clientDao.updateClient(num, numClient, nom, prenom, tel);
    }
    
    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alerte deleteClient(@PathParam("num") String numClient) {
        return clientDao.deleteClient(numClient);
    }
}
