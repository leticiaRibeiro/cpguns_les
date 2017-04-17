/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.command;

import com.cpguns.core.app.Result;
import com.cpguns.core.facade.Facade;
import com.cpguns.core.model.DomainEntity;

/**
 *
 * @author Gustavo
 */
public class ExcluirCommand implements ICommand{

    @Override
    public Result execute(DomainEntity entity) {
        Facade f = new Facade();
        return f.delete(entity);
    }
    
}
