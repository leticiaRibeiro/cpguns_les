/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.util.MyConnection;
import java.sql.Connection;

/**
 *
 * @author Leticia
 */
public class Main {
    
    public static void main(String[] args){
        
        try {
            Connection cn = MyConnection.openConnection();
            System.out.println(cn);
            if (cn != null) {
                System.out.println("SHOW");
            }else{
                System.out.println("NAO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}
