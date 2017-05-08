/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.OrderDAO;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.Carrinho;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Product;
import com.cpguns.core.model.Store;
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
public class TestaOrder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        Product p1 = new Product();
        Product p2 = new Product();
        Carrinho carrinho = new Carrinho();
        List<Product> products = new ArrayList<>();
        Costumer costumer = new Costumer();
        Card card = new Card();
        Order o = new Order();
        Store s = new Store();
        
        costumer.setId(1);
        s.setId(1);
        card.setCsc("123");
        card.setAtivo(true);
        card.setDtCreate(new Date());
        card.setMonth("1");
        card.setName("GUSTAVO B");
        card.setNumber("123456789");
        card.setYear("2020");
        
        o.setCard(card);
        o.setCostumer(costumer);
        o.setStore(s);
        o.setValorTotal(6000);
        
        p1.setId(1);
        p1.setQtdeCarrinho(1);
        p2.setId(2);
        p2.setQtdeCarrinho(1);
        products.add(p1);
        products.add(p2);
        carrinho.setProducts(products);
        
        o.setCarrinho(carrinho);
        
        try {
            orderDAO.create(o);
        } catch (SQLException ex) {
            Logger.getLogger(TestaOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
