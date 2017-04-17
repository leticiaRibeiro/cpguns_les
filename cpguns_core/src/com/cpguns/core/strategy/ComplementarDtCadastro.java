/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.model.DomainEntity;
import java.util.Date;

/**
 *
 * @author Leticia - MOMO <3
 */
public class ComplementarDtCadastro implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        
        if(entity != null){
            Date date = new Date();
            entity.setDtCreate(date);
        } else {
            return "Entidade: "+entity.getClass().getCanonicalName()+" nula!";
        }
        
        return null;
    }
}
