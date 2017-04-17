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
public class ValidarCpf implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Costumer costumer = (Costumer)entity;
        
        if(costumer.getCpf().length() != 11 || costumer.getCpf().isEmpty()){  
            return "CPF deve conter 11 dígitos!";
	}
        if(costumer.getCpf().trim().equals("")){
            return "O CPF deve ser preenchido";
        }
        
        return null;
    }
}
