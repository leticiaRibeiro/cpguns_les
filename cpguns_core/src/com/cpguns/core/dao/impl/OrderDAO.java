/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Order;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class OrderDAO extends AbstractJdbcDAO{
    
    public OrderDAO(){
        super("tb_orders", "id_order");
    }
    
    public void createTableOrder(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_orders(");
        sql.append("id_order serial primary key, ");
        sql.append("id_sto INTEGER REFERENCES tb_stores(id_store), ");
        sql.append("id_cos INTEGER REFERENCES tb_costumers(id_costumer), ");
        sql.append("id_credcard INTEGER REFERENCES tb_cards(id_card), ");
        sql.append("valorTotal decimal,");
        sql.append("dtCreate date); ");
        
        sql.append("CREATE TABLE tb_order_product(");
        sql.append("id_order INTEGER REFERENCES tb_orders(id_order), ");
        sql.append("id_product INTEGER REFERENCES tb_products(id_product));");
        
        try{
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        }catch(SQLException ex){
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(DomainEntity entity) throws SQLException {
        
        PreparedStatement pst = null;
        CardDAO cardDAO = new CardDAO();
        
        Order order = (Order) entity;
        
                
        
        
        
    }

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {
        
        return null;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
    
    }
    
}
