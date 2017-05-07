/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.DomainEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class CardDAO extends AbstractJdbcDAO{
    
    public CardDAO(){
        super("tb_cards", "id_card");
    }
    
    public void createTableCard(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_cards(");
        sql.append("id_card serial primary key, ");
        sql.append("name text, ");
        sql.append("number text not null, ");
        sql.append("month text, ");
        sql.append("year text, ");
        sql.append("csc text) ");
        
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
        Card card = (Card) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_cards(name, number, month, year, csc )");
            sql.append(" VALUES (?,?,?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(), 
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, card.getName());
            pst.setString(2, card.getNumber());
            pst.setString(3, card.getMonth());
            pst.setString(4, card.getYear());
            pst.setString(5, card.getCsc());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idCartao = 0;
            if(rs.next()){
                idCartao = rs.getInt("id_card");
            }
            card.setId(idCartao);
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
        
        openConnection();
        PreparedStatement pst = null;
        Card card = (Card) entity;
        List<DomainEntity> cards = new ArrayList<>();
        boolean ehEspecifico = false;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_cards ");
            if (card.getId() != 0) { 
                sql.append(" WHERE id_card=?");
                ehEspecifico = true;
            }
            
            pst = connection.prepareStatement(sql.toString());
            if (ehEspecifico) { // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, card.getId());
            }
            
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Card c = new Card();
                
                c.setId(rs.getInt("id_card"));
                c.setName(rs.getString("name"));
                c.setNumber(rs.getString("number"));
                c.setMonth(rs.getString("month"));
                c.setYear(rs.getString("year"));
                c.setCsc(rs.getString("csc"));
                cards.add(c);
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
        
        
        return cards;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Card card = (Card) entity;
        
        try {
            connection.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_cards SET name=?, number=?, month=?, year=?, csc=?");
            sql.append(" WHERE id_card=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, card.getName());
            pst.setString(2, card.getNumber());
            pst.setString(3, card.getMonth());
            pst.setString(4, card.getYear());
            pst.setString(5, card.getCsc());
            pst.setInt(6, card.getId());
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
    
}
