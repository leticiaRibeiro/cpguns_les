/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Manufacturer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class ManufacturerDAO extends AbstractJdbcDAO{
    
    public ManufacturerDAO(){
        super("tb_manufacturer", "id_manufacturer");
    }
    
    public void createTableManufacturer(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_manufacturer(");
        sql.append("id_manufacturer serial primary key, ");
        sql.append("name text not null, ");
        sql.append("dtCreate date) ");
        
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
        
        openConnection();
        PreparedStatement pst = null;
        Manufacturer manufacturer = (Manufacturer) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_manufacturer(name, dtCreate)");
            sql.append(" VALUES (?,?)");
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, manufacturer.getName());
            Timestamp time = new Timestamp(manufacturer.getDtCreate().getTime());
            pst.setTimestamp(2, time);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            int idManu = 0;
            if(rs.next()){
                idManu = rs.getInt("id_manufacturer");
            }
            manufacturer.setId(idManu);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try {
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Manufacturer manufacturer = (Manufacturer) entity;
        List<DomainEntity> manufacturers = new ArrayList<>();
        boolean ehEspecifico = false;
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_manufacturer");
            // vamos verificar se estamos buscando um fabricante especifico
            // é especifico?
            if(manufacturer.getId() != 0){ // int NUNCA será Null, então se for zero é pq nao foi especificado 
                sql.append(" WHERE id_manufacturer=?");
                ehEspecifico = true;
            }
            
            pst = connection.prepareStatement(sql.toString());
            if(ehEspecifico){ // precisamos settar o ID se for true
                pst.setInt(1, manufacturer.getId());
            }
            
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo...
            while(rs.next()){
                Manufacturer m = new Manufacturer();
                // pegamos os valores das colunas e settamos no objeto de Manufacturer
                m.setName(rs.getString("name"));
                m.setId(rs.getInt("id_manufacturer"));
                java.sql.Date dtCreateLong = rs.getDate("dtCreate");
                Date dtCreate = new Date(dtCreateLong.getTime());
                m.setDtCreate(dtCreate);
                
                // add o fabricante na lista
                manufacturers.add(m);
            }
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
        return manufacturers;
    } // read List

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Manufacturer manufacturer = (Manufacturer) entity;
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_manufacturer SET name=?");
            sql.append(" WHERE id_manufacturer=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, manufacturer.getName());
            pst.setInt(2, manufacturer.getId());
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
    } // update
}
