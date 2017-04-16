/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;

/**
 *
 * @author Leticia
 */
public class ValidarDadosObrigatorios implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Costumer costumer = (Costumer)entity;
        
        if(costumer.getName() == null || costumer.getName().isEmpty()){
            return "O nome deve ser preechido";
        }
        
        return null;
    }
    
}
