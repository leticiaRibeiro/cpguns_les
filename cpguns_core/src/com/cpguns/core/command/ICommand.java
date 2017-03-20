/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.command;

import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Resultado;

/**
 *
 * @author Gustavo
 */
public interface ICommand {
    
    public Resultado execute(DomainEntity entity);
    
}
