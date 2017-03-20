/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class UserDAO extends AbstractJdbcDAO{
    
    public UserDAO(){
        super("tb_users", "id_user");
    }   
    
    public void createTableUser(){
        
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_users(");
        sql.append("email text, ");
        sql.append("password text, ");
        sql.append("level integer, ");
        sql.append("id_user serial primary key) ");
        
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
        User user = (User) entity;
        
        try{
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_users(email, password, level)");
            sql.append(" VALUES (?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(), 
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getLevel());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            int idUs = 0;
            if(rs.next()){
                idUs = rs.getInt("id_user");
            }
            user.setId(idUs);        
            connection.commit();
        
        }catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
            
        }finally{
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
    
        openConnection();
        PreparedStatement pst = null;
        User user = (User) entity;
        List<DomainEntity> users = new ArrayList<>();
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_users WHERE email=? and password=? and level=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getLevel());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                User u = new User();
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setLevel(rs.getInt("level"));
                users.add(u);
            }
            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                pst.close();
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        User user = (User) entity;
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_users SET email=?, password=?, level=?");
            sql.append(" WHERE id_user=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getLevel());
            pst.setInt(4, user.getId());
            pst.executeUpdate();
            connection.commit();            
            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                pst.close();
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
