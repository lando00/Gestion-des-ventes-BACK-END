/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

import com.project.dao.DashboardDao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Orlando
 */
@Path("/dashboard")
public class DashboardService {
    
    DashboardDao dashboardDao = new DashboardDao();

    @GET
    @Path("/total_client")
    @Produces(MediaType.APPLICATION_JSON)
    public int totalClient() {
        return dashboardDao.getTotalClient();
    }
    
    @GET
    @Path("/total_materiel")
    @Produces(MediaType.APPLICATION_JSON)
    public int totalMateriel() {
        return dashboardDao.getTotalMateriel();
    }
    
    @GET
    @Path("/total_vente")
    @Produces(MediaType.APPLICATION_JSON)
    public int totalVente() {
        return dashboardDao.getTotalVente();
    }
    
    @GET
    @Path("/total_ca")
    @Produces(MediaType.APPLICATION_JSON)
    public int totalCA() {
        return dashboardDao.getTotalChiffreAffaire();
    }
}
