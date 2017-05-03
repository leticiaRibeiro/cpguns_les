/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.StoreDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.City;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.State;
import com.cpguns.core.model.Store;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class MainStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StoreDAO lojaDAO = new StoreDAO();
        Store s = new Store();
        Address a = new Address();
        City c = new City();
        State st = new State(); 
        
        s.setId(1);
        a.setId(4);
        a.setStreet("Rua Quatro");
        a.setNumber("33");
        a.setComplement("Apto 12");
        a.setZip("08999000");
        a.setNeighborhood("Centro");
        c.setName("São Paulo");
        st.setName("São Paulo");
        c.setState(st);
        a.setCity(c);
        s.setName("Casa do Lunger 2");
        s.setAddress(a);
        
        try {
            List<DomainEntity> stores = new ArrayList<>();
            stores = lojaDAO.read(s);
            
            for (DomainEntity store : stores) {
                Store sto = (Store) store;
                System.out.println("ID: " + sto.getId());
                System.out.println("Nome da Loja: " + sto.getName());
                System.out.println("ID do Endereço: " + sto.getAddress().getId());
                System.out.println("Rua: " + sto.getAddress().getStreet());
                System.out.println("Numero: " + sto.getAddress().getNumber());
                System.out.println("Complemento: " + sto.getAddress().getComplement());
                System.out.println("CEP: " + sto.getAddress().getZip());
                System.out.println("Bairro: " + sto.getAddress().getNeighborhood());
                System.out.println("Cidade: " + sto.getAddress().getCity().getName());
                System.out.println("Estado: " + sto.getAddress().getCity().getState().getName());
                System.out.println("Data Cadastro: " + sto.getDtCreate());
                System.out.println("--------------------------------------------");
                
                stores.size();
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
