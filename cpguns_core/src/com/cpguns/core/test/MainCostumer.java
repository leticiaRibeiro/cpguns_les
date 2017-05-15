/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.User;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Leticia
 */
public class MainCostumer {
    
    public static void main(String[] args){
        
        Costumer c = new Costumer();
        Autorizacao a = new Autorizacao();
        c.setId(1);
        User u = new User("maria@hotmail.com", "maria", 1);
        u.setId(6);
        c.setUser(u);
        c.setName("Maria de Souza");
        c.setCpf("3325526627");
        c.setRg("901112349");
        c.setGenre("feminino");
        c.setPhoneNumber("47009719");
        a.setAutorizacao("987654321");
        c.setAutorizacao(a);
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
