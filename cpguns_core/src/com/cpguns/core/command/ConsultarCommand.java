/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.command;

import com.cpguns.core.app.Result;
import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.model.DomainEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class ConsultarCommand implements ICommand{

    @Override
    public Result execute(DomainEntity entity) {
        Result result = new Result();
        CostumerDAO dao = new CostumerDAO();
        try {
            result.setEntities(dao.read(entity));
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
