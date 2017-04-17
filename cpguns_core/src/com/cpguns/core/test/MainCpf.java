/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.model.Costumer;
import com.cpguns.core.strategy.ValidarCpf;

/**
 *
 * @author Leticia
 */
public class MainCpf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Costumer c = new Costumer();
        ValidarCpf vCpf = new ValidarCpf();
        
        c.setCpf("40562486801");
        
        System.out.println(vCpf.process(c));
        
        
    }
    
}
