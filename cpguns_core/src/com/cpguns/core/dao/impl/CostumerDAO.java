/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class CostumerDAO extends AbstractJdbcDAO{

    public CostumerDAO(){
        super("tb_costumer", "id_costumer");
    }
    
    public void createTableCostumer(){
        
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_costumers(");
        sql.append("id_costumer serial primary key, ");
        sql.append("name text not null, ");
        sql.append("cpf text, ");
        sql.append("rg text, ");
        sql.append("genre text, ");
        sql.append("dt_birth date, ");
        sql.append("phone_number text, ");
        sql.append("id_us INTEGER REFERENCES tb_users(id_user), ");
        sql.append("dt_create date) ");
        
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
        
        
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;
        UserDAO usDAO = new UserDAO();
        
        try{
            
            usDAO.create(costumer.getUser());
            openConnection();
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_costumers(name, cpf, rg, genre, dt_birth, phone_number, id_us, dt_create)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(), 
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, costumer.getName());
            pst.setString(2, costumer.getCpf());
            pst.setString(3, costumer.getRg());
            pst.setString(4, costumer.getGenre());
            Timestamp timedtBirth = new Timestamp(costumer.getDtBirth().getTime());
            pst.setTimestamp(5, timedtBirth);
            pst.setString(6, costumer.getPhoneNumber());
            pst.setInt(7, costumer.getUser().getId());
            Timestamp timedtCreate = new Timestamp(costumer.getDtCreate().getTime());
            pst.setTimestamp(8, timedtCreate);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            int idCos = 0;
            if(rs.next()){
                idCos = rs.getInt("id_costumer");
            }
            costumer.setId(idCos);
            connection.commit();            
        
        }catch(Exception e){
            
        }
        
    }

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {
        
        return null;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
    }
    
}
