/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CardDAO;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.DomainEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class MainCard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CardDAO carDAO = new CardDAO();
        Card c = new Card();
        
        //c.setId(4);
        c.setName("GUSTAVO S BEZERRA");
        c.setNumber("1111 3333 5555 7777");
        c.setMonth("03");
        c.setYear("23");
        c.setCsc("111");
        
        try {
            List<DomainEntity> cards = new ArrayList<>();
            cards = carDAO.read(c);
            
            for (DomainEntity card : cards) {
                Card car = (Card) card;
                System.out.println("ID: " + car.getId());
                System.out.println("Nome: " + car.getName());
                System.out.println("Numero: " + car.getNumber());
                System.out.print("Mes: " + car.getMonth());
                System.out.println("  Ano: " + car.getYear());
                System.out.println("CSC: " + car.getCsc());
                System.out.println("-------------------------------------");
                
                cards.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
