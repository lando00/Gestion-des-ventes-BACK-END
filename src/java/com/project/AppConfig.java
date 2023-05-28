/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project;

import com.project.config.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Orlando
 */
public class AppConfig extends ResourceConfig {
    public AppConfig(){
        register(CorsFilter.class);
    }
} 
