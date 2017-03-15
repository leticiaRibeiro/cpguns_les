/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
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
        Manufacturer manu = new Manufacturer();
        manu.setId(4);
        //prod.setId();
        prod.setName("Rifle de Precisão Barrett M82A1");
        prod.setDescription("Rifle");
        prod.setCaliber(".50");
        prod.setWeight((float) 5.34);
        prod.setAction("auto");
        prod.setOrigin("USA");
        prod.setModel("M82A1");
        prod.setCapacity("7 tirinhos");
        prod.setManufacturer(manu);
        prod.setDtCreate(new Date());
        
        
        
        ProductDAO prodDAO = new ProductDAO();
        
        
        
        try {
            // variável do tipo list
            List<DomainEntity> products = new ArrayList<>();
            //prodDAO.create(prod);
            products = prodDAO.read(prod);
            //prodDAO.criarTabela();
                
            // para cada produto é um item da lista
            for(DomainEntity produto : products){
                Product p = (Product) produto;
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getName());
                System.out.println("Descrição: " + p.getDescription());
                System.out.println("Calibre: " + p.getCaliber());
                System.out.println("Peso: " + p.getWeight());
                System.out.println("Ação: " + p.getAction());
                System.out.println("Origem: " + p.getOrigin());
                System.out.println("Capacidade: " + p.getCapacity());
                System.out.println("Fabricante: " + p.getManufacturer().getName());
                System.out.println("ID Fabricante: " + p.getManufacturer().getId());
                System.out.println("Data Cadastro: " + p.getDtCreate());
                System.out.println("--------------------------------");             
            }
            products.size();
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
}
