/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Address;
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
public class AddressDAO extends AbstractJdbcDAO{
    
    public AddressDAO(){
        super("tb_addresses", "id_address");
    }
    
    public void createTableAddress(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_addresses(");
        sql.append("id_address serial primary key, ");
        sql.append("street text not null, ");
        sql.append("number text, ");
        sql.append("zip text, ");
        sql.append("complement text, ");
        sql.append("neighborhood text, ");
        sql.append("city text, ");
        sql.append("state text, ");
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
        Address address = (Address) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_addresses(street, number, zip, complement, neighborhood, city, state, dtCreate)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, address.getStreet());
            pst.setString(2, address.getNumber());
            pst.setString(3, address.getZip());
            pst.setString(4, address.getComplement());
            pst.setString(5, address.getNeighborhood());
            pst.setString(6, address.getCity().getName());
            pst.setString(7, address.getCity().getState().getName());
            Timestamp time = new Timestamp(address.getDtCreate().getTime());
            pst.setTimestamp(8, time);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            int idAdd = 0;
            if(rs.next()){
                idAdd = rs.getInt("id_address");
            }
            address.setId(idAdd);
            connection.commit();
            
        } catch (Exception e) {
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
