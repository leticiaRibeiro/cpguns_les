/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leticia
 */
public class MyConnection {
    
    // vou abrir uma connexão usando o método estático OpenConnection da classe Connection
    // que retorna alguma coisa do tipo Connection.
    public static Connection openConnection() throws SQLException, ClassNotFoundException{
        
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/cpguns";
        String user = "postgres";
        String password = "leticia";
        Class.forName(driver);
        
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    } 
}
