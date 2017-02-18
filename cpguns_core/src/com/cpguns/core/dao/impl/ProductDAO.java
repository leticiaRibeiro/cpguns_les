/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class ProductDAO extends AbstractJdbcDAO{

    public ProductDAO(){
        super("tb_product", "id_product");
        
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_products(");
        sql.append("id_product serial primary key, ");
        sql.append("name text not null) ");
//        sql.append("description text, ");
//        sql.append("caliber text not null, ");
//        sql.append("weight float, ");
//        sql.append("action text, ");
//        sql.append("origin text, ");
//        sql.append("model text, ");
//        sql.append("capacity text, ");
//        sql.append("manufacturer text not null) ");
//        
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Product product = (Product) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_products(name)");
            sql.append(" VALUES (?)");
            
            pst = connection.prepareStatement(sql.toString(), 
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, product.getName());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            int idProd = 0;
            if(rs.next()){
                idProd = rs.getInt(1);
            }
            product.setId(idProd);
            connection.commit();            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try{
                pst.close();
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Product product = (Product) entity;
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_products SET name=?");
            sql.append("WHERE id_product=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, product.getName());
            pst.executeUpdate();
            connection.commit();            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try{
                pst.close();
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
    
    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {
        
        
        return null;
    }

    
    
    
}
