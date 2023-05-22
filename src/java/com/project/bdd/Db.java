/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.bdd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Orlando
 */
public class Db {

    public Db() {
    }

    public Connection connectDb() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
