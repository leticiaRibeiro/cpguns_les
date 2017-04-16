/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.model.DomainEntity;

/**
 *
 * @author Leticia
 */
public interface IStrategy {
    
    public String process(DomainEntity entity);
    
}
