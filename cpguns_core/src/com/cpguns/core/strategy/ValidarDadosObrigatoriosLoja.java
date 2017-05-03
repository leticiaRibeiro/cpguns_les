/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Store;

/**
 *
 * @author Leticia
 */
public class ValidarDadosObrigatoriosLoja implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Store store = (Store)entity;
        
        String name = store.getName();
        
        
        return null;
    }
    
}
