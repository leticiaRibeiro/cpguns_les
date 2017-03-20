/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.User;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class MainCostumer {
    
    public static void main(String[] args){
        
        Costumer c = new Costumer();
        User u = new User("leticia@hotmail.com", "leticiaar", 1);
        c.setUser(u);
        c.setName("Leticia Ribeiro");
        c.setCpf("890");
        c.setRg("789");
        c.setGenre("feminino");
        c.setPhoneNumber("47888888");
        c.setDtCreate(new Date());
        c.setDtBirth(new Date());
        
        
        CostumerDAO cDAO = new CostumerDAO();
        
        //cDAO.createTableCostumer();
        try {
            cDAO.create(c);
        } catch (SQLException ex) {
            Logger.getLogger(MainCostumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
