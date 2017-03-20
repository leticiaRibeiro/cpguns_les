/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class CostumerDAO extends AbstractJdbcDAO{

    public CostumerDAO(){
        super("tb_costumer", "id_costumer");
    }
    
    public void createTableCostumer(){
        
    }
    
    @Override
    public void create(DomainEntity entidade) throws SQLException {
        
    }

    @Override
    public List<DomainEntity> read(DomainEntity entidade) throws SQLException {
        
        return null;
    }

    @Override
    public void update(DomainEntity entidade) throws SQLException {
        
    }
    
}
