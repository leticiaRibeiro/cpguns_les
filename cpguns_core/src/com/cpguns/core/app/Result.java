/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.app;

import com.cpguns.core.model.DomainEntity;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class Result extends ApplicationEntity{
    
    private String msg;
    private List<DomainEntity> entities;
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public List<DomainEntity> getEntidades() {
        return entities;
    }
    
    public void setEntidades(List<DomainEntity> entidades) {
        this.entities = entities;
    }
    
}
