/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.User;
import com.cpguns.core.strategy.ValidarEmail;

/**
 *
 * @author Leticia
 */
public class MainStrategy {
    
    public static void main(String[] args){
        
        ValidarEmail vEmail = new ValidarEmail();
        Costumer c = new Costumer();
        User u = new User();
                
        u.setEmail("@outlook.hotmail");
        c.setUser(u);
        
        System.out.println(vEmail.process(c));
    }
    
}
