/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.facade;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import java.util.List;

/**
 *
 * @author Leticia
 */
public interface IFacade {
    
    public Result create(DomainEntity entity);
    public Result read(DomainEntity entity);
    public Result update(DomainEntity entity);
    public Result delete(DomainEntity entity);
    
}
