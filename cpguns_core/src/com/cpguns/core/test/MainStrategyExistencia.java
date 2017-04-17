/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.User;
import com.cpguns.core.strategy.ValidarExistencia;

/**
 *
 * @author Leticia
 */
public class MainStrategyExistencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ValidarExistencia vExistencia = new ValidarExistencia();
        User u = new User();
        Costumer c = new Costumer();
        
        u.setEmail("leticia@outlook.com");
        c.setUser(u);
        c.setRg("991190190");
        c.setCpf("12312312333");
        
        System.out.println(vExistencia.process(c));
        
    }
    
}
