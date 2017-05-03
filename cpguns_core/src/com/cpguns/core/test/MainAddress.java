/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AddressDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.City;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.State;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class MainAddress {
    
    public static void main(String[] args){
        
        AddressDAO adDAO = new AddressDAO();
        City c = new City();
        State s = new State();
        
        // criar a tabela de endereço
        //adDAO.createTableAddress();
        
        // ---- Criar um novo Endereço ----
        Address add = new Address();
        //add.setId(2);
        add.setStreet("Avenida Japao Japao Japao");
        add.setNumber("1234");
        add.setComplement("Apto 123");
        add.setZip("08730000");
        add.setNeighborhood("Alto do Ipiranga");
        c.setName("Mogi das Cruzes");
        s.setName("SP");
        c.setState(s);
        add.setCity(c);
        add.setDtCreate(new Date());
        
        try {
            List<DomainEntity> addresses = new ArrayList<>();
            addresses = adDAO.read(add);
            
            for (DomainEntity address : addresses) {
                Address end = (Address) address;
                System.out.println("ID: " + end.getId());
                System.out.println("Rua: " + end.getStreet());
                System.out.println("Numero: " + end.getNumber());
                System.out.println("Complemento: " + end.getComplement());
                System.out.println("CEP: " + end.getZip());
                System.out.println("Bairro: " + end.getNeighborhood());
                System.out.println("Cidade: " + end.getCity().getName());
                System.out.println("Estado: " + end.getCity().getState().getName());
                System.out.println("Data Cadastro: " + end.getDtCreate());
                System.out.println("--------------------------------------------");
                
                addresses.size();
            }
        } catch(Exception e){
            e.printStackTrace();   
        }
        
        
        
        
        
        
    }
    
}
