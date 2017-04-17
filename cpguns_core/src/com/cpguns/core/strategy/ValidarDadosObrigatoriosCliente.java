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
public class ValidarDadosObrigatoriosCliente implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Costumer costumer = (Costumer)entity;
        
        String name = costumer.getName();
        String genre = costumer.getGenre();
        String rg = costumer.getRg();
        String phoneNumber = costumer.getPhoneNumber();
        String email = costumer.getUser().getEmail();
        String password = costumer.getUser().getPassword();
        
        if(name == null || name.isEmpty() || name.trim().equals("")){
            return "O nome deve ser preechido";
        }
        if(genre == null){
            return "Escolha um gênero";
        }
        if(rg == null || rg.isEmpty() || rg.trim().equals("")){
            return "O RG deve ser preenchido";
        }
        if(phoneNumber == null || phoneNumber.isEmpty()){
            return "Preencha com um telefone de contato";
        }
        if(email == null || email.trim().equals("")){
            return "O email deve ser preenchido";
        }
        if(password == null || password.trim().equals("")){
            return "A senha deve ser preenchida";
        }
        
        return null;
    }    
}
