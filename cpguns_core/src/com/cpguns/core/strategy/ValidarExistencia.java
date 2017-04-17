/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.strategy;

import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class ValidarExistencia implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
    
        StringBuilder sb = new StringBuilder();
        CostumerDAO costDAO = new CostumerDAO();
        Costumer costumer = (Costumer) entity;
        try {
            if(costDAO.verificaExistenciaCpf(costumer)){
                sb.append("Este CPF já existe!\n");
            }
            if(costDAO.verificaExistenciaRg(costumer)){
                sb.append("Este RG já existe!\n");
            }
            if(costDAO.verificaExistenciaEmail(costumer)){
                sb.append("Este Email já existe!\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidarExistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(sb.length() == 0){
            return null;
        }else{
            return sb.toString();
        }
    }
    
}
