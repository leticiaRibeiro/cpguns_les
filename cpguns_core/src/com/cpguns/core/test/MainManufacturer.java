/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ManufacturerDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Manufacturer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class MainManufacturer {
    
    public static void main(String[] args){
        
        //----- criar a tabela de fabricante -----
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        //manuDAO.createTableManufacturer();
        
        
        //---- Criar Fabricante ----
        Manufacturer manuf = new Manufacturer();
        //manuf.setId(3);
        manuf.setName("Barrett");
        manuf.setDtCreate(new Date());
        
        try {
            manuDAO.create(manuf);
            //List<DomainEntity> manufacturers = new ArrayList<>();
            //manufacturers = manuDAO.read(manuf);
            // cada fabricante é um item da lista
            /*for (DomainEntity manufacturer : manufacturers) {
                Manufacturer m = (Manufacturer) manufacturer;
                System.out.println("ID: " + m.getId());
                System.out.println("Fabricante: " + m.getName());
                System.out.println("Data Cadastro: " + m.getDtCreate());
                System.out.println("--------------------------------------------");
            }*/
            //manufacturers.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
