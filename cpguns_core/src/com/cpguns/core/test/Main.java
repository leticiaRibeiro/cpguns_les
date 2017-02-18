/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.Product;
import java.sql.SQLException;

/**
 *
 * @author Leticia
 */
public class Main {
    
    public static void main(String[] args){
        
        Product prod = new Product();
        prod.setName("Colt M4");
        
        
        ProductDAO prodDAO = new ProductDAO();
        // EXECUTAR O CRIAR A TABELA SOMENTE QUANDO PRECISAR CRIA-LA! Caso contrário, comente esta linha.
        prodDAO.criarTabela();
        
        
        try {
            prodDAO.create(prod);
	} catch (SQLException e) {
            e.printStackTrace();
	}
        
    }
}
