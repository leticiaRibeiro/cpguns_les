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
            Timestamp time = new Timestamp(new Date().getTime());
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
        Address address = (Address) entity;
        List<DomainEntity>addresses = new ArrayList<>();
        boolean ehEspecifico = false;
        
        try{
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_addresses");
            // Estamos buscando um endereço especifico?
            if(address.getId() != 0){ // pq != 0? INT é um tipo primitivo. Ou seja, NUNCA será null. Caso esteja 0, é pq não foi informado um ID especifico.
                sql.append(" WHERE id_address=?"); // vamos procurar um endereço especifico
                ehEspecifico = true;
            } 
            
            pst = connection.prepareStatement(sql.toString());
            if(ehEspecifico){ // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, address.getId());
            }
            
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while(rs.next()){
                // novo registro, novo product!
                Address a = new Address();
                City c = new City();
                State s = new State();
                // pegamos os valores das colunas e settamos no objeto de Product - Se a coluna for int: rs.getInt("NOME DA COLUNA")
                a.setId(rs.getInt("id_address"));
                a.setStreet(rs.getString("street"));
                a.setNumber(rs.getString("number"));
                a.setZip(rs.getString("zip"));
                a.setComplement(rs.getString("complement"));
                a.setNeighborhood(rs.getString("neighborhood"));
                s.setName(rs.getString("state"));
                c.setState(s);
                c.setName(rs.getString("city"));
                a.setCity(c);
                java.sql.Date dtCreateLong = rs.getDate("dtCreate");
                Date dtCreate = new Date(dtCreateLong.getTime());
                a.setDtCreate(dtCreate);
                // adicionamos o produto na lista, que iremos retornar com todos os valores encontrados...
                addresses.add(a);
            }
        } catch(Exception e){
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
        return addresses;
        
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Address address = (Address) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_addresses SET street=?, number=?, zip=?, complement=?, neighborhood=?, city=?, state=?");
            sql.append(" WHERE id_address=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, address.getStreet());
            pst.setString(2, address.getNumber());
            pst.setString(3, address.getZip());
            pst.setString(4, address.getComplement());
            pst.setString(5, address.getNeighborhood());
            pst.setString(6, address.getCity().getName());
            pst.setString(7, address.getCity().getState().getName());
            pst.setInt(8, address.getId());
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
            try {
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
