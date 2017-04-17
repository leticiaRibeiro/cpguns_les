/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leticia
 */
public class ValidarEmail implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Costumer costumer = (Costumer)entity;
        
        if(validar(costumer.getUser().getEmail())){
            return null;
        } else {
            return "Digite um email válido";
        }
    }
    
    
    public static boolean validar(String email)
    {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
