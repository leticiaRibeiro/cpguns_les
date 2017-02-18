/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class Main {
    
    public static void main(String[] args){
        
        Product prod = new Product();
        prod.setId(3);
        prod.setName("Germany Lunger");
        prod.setDescription("Revolverr");
        prod.setCaliber(".388");
        prod.setWeight((float) 3.21);
        prod.setAction("autop");
        prod.setOrigin("Alemanhaa");
        prod.setModel("lungergermmmm");
        prod.setCapacity("5 tirosinhos ");
        prod.setDtCreate(new Date());
        
        
        
        ProductDAO prodDAO = new ProductDAO();
        // EXECUTAR O CRIAR A TABELA SOMENTE QUANDO PRECISAR CRIA-LA! Caso contrário, comente esta linha.
        //prodDAO.criarTabela();
        
        
        try {
            // variável do tipo list
            //List<DomainEntity> products = new ArrayList<>();
            //products = prodDAO.read(prod);
            prodDAO.delete(prod);            
            // para cada produto é um item da lista
            //for(DomainEntity produto : products){
                /*Product p = (Product) produto;
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getName());
                System.out.println("Descrição: " + p.getDescription());
                System.out.println("Calibre: " + p.getCaliber());
                System.out.println("Peso: " + p.getWeight());
                System.out.println("Ação: " + p.getAction());
                System.out.println("Origem: " + p.getOrigin());
                System.out.println("Capacidade: " + p.getCapacity());
                System.out.println("Data Cadastro: " + p.getDtCreate());
                System.out.println("--------------------------------");*/
                
            //}
            //products.size();
	} catch (Exception e) {
            e.printStackTrace();
	}
        
    }
}
