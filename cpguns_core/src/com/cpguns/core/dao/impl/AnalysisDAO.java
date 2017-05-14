/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class AnalysisDAO extends AbstractJdbcDAO{

    public AnalysisDAO() {
        super("tb_acessos", "id_acesso");
    }
    
    public void createTableAcesso() {
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_acessos(");
        sql.append("id_acesso serial primary key, ");
        sql.append("id_prod INTEGER REFERENCES tb_products(id_product), ");
        sql.append("tipo text, ");
        sql.append("dtAcesso date); ");

        
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTableAutorizacao() {
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_autorizacoes(");
        sql.append("autorizacao text, ");
        sql.append("tipo text); ");


        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
