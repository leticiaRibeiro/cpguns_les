/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CardDAO;
import com.cpguns.core.dao.impl.OrderDAO;
import com.cpguns.core.model.Order;

/**
 *
 * @author Leticia
 */
public class MainOrder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrderDAO orDAO = new OrderDAO();
        CardDAO cardDAO = new CardDAO();
        Order o = new Order();
        cardDAO.createTableCard();
        orDAO.createTableOrder();
    }
    
}
