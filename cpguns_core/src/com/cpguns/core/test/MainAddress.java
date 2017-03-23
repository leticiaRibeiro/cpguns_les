/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AddressDAO;
import com.cpguns.core.model.Address;

/**
 *
 * @author Leticia
 */
public class MainAddress {
    
    public static void main(String[] args){
        
        Address ad = new Address();
        AddressDAO adDAO = new AddressDAO();

        
        
        adDAO.createTableAddress();
    }
    
}
