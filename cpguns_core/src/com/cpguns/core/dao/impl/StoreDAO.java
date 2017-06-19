/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.City;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.State;
import com.cpguns.core.model.Store;
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
public class StoreDAO extends AbstractJdbcDAO{
    
    public StoreDAO(){
        super("tb_stores", "id_store");
    }
    
    public void createTableStore(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_stores(");
        sql.append("id_store serial primary key, ");
        sql.append("name text not null, ");
        sql.append("dtCreate date, ");
        sql.append("id_user INTEGER REFERENCES tb_users(id_user), ");
        sql.append("id_add INTEGER REFERENCES tb_addresses(id_address)) ");
        
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
        Store store = (Store) entity;
        AddressDAO addDAO = new AddressDAO();
        UserDAO userDAO = new UserDAO();
        
        try {
            userDAO.create(store.getUser());
            addDAO.create(store.getAddress());
            openConnection();
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_stores(name, dtCreate, id_add, id_user)");
            sql.append(" VALUES (?,?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, store.getName());
            Timestamp timedtCreate = new Timestamp(new Date().getTime());
            pst.setTimestamp(2, timedtCreate);
            pst.setInt(3, store.getAddress().getId());
            pst.setInt(4, store.getUser().getId());
            pst.executeUpdate();
            
             ResultSet rs = pst.getGeneratedKeys();
            int idSto = 0;
            if (rs.next()) {
                idSto = rs.getInt("id_store");
            }
            store.setId(idSto);
            connection.commit();
            
        } catch (Exception e){
            
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
        Store store = (Store) entity;
        List<DomainEntity> stores = new ArrayList<>();
        boolean ehEspecifico = false;
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT s.*, a.* FROM tb_stores s INNER JOIN tb_addresses a on s.id_add = a.id_address");
            if (store.getId() != 0) { // pq != 0? INT é um tipo primitivo. Ou seja, NUNCA será null. 
                // Caso esteja 0, é pq não foi informado um ID especifico.
                sql.append(" WHERE id_store=?"); // vamos procurar um cliente especifico
                ehEspecifico = true;
            }
            
            pst = connection.prepareStatement(sql.toString());
            if (ehEspecifico) { // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, store.getId());
            }
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Store s = new Store();
                Address a = new Address();
                City c = new City();
                State st = new State();
                
                s.setName(rs.getString("name"));
                s.setId(rs.getInt("id_store"));
                java.sql.Date dtCreateLong = rs.getDate("dtCreate");
                Date dtCreate = new Date(dtCreateLong.getTime());
                s.setDtCreate(dtCreate);
                a.setId(rs.getInt("id_add"));
                a.setStreet(rs.getString("street"));
                a.setNumber(rs.getString("number"));
                a.setComplement(rs.getString("complement"));
                a.setZip(rs.getString("zip"));
                a.setNeighborhood(rs.getString("neighborhood"));
                c.setName(rs.getString("city"));
                st.setName(rs.getString("state"));
                c.setState(st);
                a.setCity(c);
                s.setAddress(a);
                
                
                stores.add(s);
            }
            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        return stores;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
    
        openConnection();
        PreparedStatement pst = null;
        Store store = (Store) entity;
        AddressDAO addDAO = new AddressDAO();
        
        try {
            addDAO.update(store.getAddress());
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_stores SET name=?");
            sql.append(" WHERE id_store=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, store.getName());
            pst.setInt(2, store.getId());
            pst.executeUpdate();
            connection.commit();
            
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
