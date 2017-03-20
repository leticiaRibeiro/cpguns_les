/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class MainCostumer {
    
    public static void main(String[] args){
        
        Costumer c = new Costumer();
        c.setId(1);
        User u = new User("gustavo@hotmail.com", "gu", 1);
        u.setId(6);
        c.setUser(u);
        c.setName("Gustavo Bezerra");
        c.setCpf("4051231231");
        c.setRg("87162464");
        c.setGenre("masculino");
        c.setPhoneNumber("47009718");
        c.setDtCreate(new Date());
        c.setDtBirth(new Date());
        
        
        CostumerDAO cDAO = new CostumerDAO();
        try {
            //cDAO.update(c);
            //cDAO.createTableCostumer();
//        try {
//            List<DomainEntity> costumers = new ArrayList<>();
            cDAO.create(c);
//            costumers = cDAO.read(c);
//            
//            for (DomainEntity costumer : costumers) {
//                Costumer cos = (Costumer) costumer;
//                User us = new User();
//                System.out.println("ID: " + cos.getId());
//                System.out.println("Nome: " + cos.getName());
//                System.out.println("CPF: " + cos.getCpf());
//                System.out.println("RG: " + cos.getRg());
//                System.out.println("Telefone: " + cos.getPhoneNumber());
//                System.out.println("Dt Nacimento: " + cos.getDtBirth());
//                System.out.println("Email: " + cos.getUser().getEmail());
//                System.out.println("Senha: " + cos.getUser().getPassword());
//                System.out.println("Nível: " + cos.getUser().getLevel());
//                System.out.println("ID_USER: " + cos.getUser().getId());
//                System.out.println("--------------------------------------------");
//              
//            }
//            costumers.size();
//           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        } catch (SQLException ex) {
//            Logger.getLogger(MainCostumer.class.getName()).log(Level.SEVERE, null, ex);
//        }   
        //}
    }   
}
