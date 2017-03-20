/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
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
public class ProductDAO extends AbstractJdbcDAO{

    public ProductDAO(){
        super("tb_products", "id_product");
    }
    
    public void createTableProduct(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_products(");
        sql.append("id_product serial primary key, ");
        sql.append("name text not null, ");
        sql.append("description text, ");
        sql.append("caliber text not null, ");
        sql.append("weight float(2), ");
        sql.append("action text, ");
        sql.append("origin text, ");
        sql.append("model text, ");
        sql.append("capacity text, ");
        sql.append("id_manuf INTEGER REFERENCES tb_manufacturer(id_manufacturer), ");
        sql.append("dtCreate date) ");
        
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
            sql.append("INSERT INTO tb_products(name, description, caliber, weight, action, origin, model, capacity, dtCreate, id_manuf )");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pst = connection.prepareStatement(sql.toString(), 
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, product.getName());
            pst.setString(2, product.getDescription());
            pst.setString(3, product.getCaliber());
            pst.setFloat(4, product.getWeight());
            pst.setString(5, product.getAction());
            pst.setString(6, product.getOrigin());
            pst.setString(7, product.getModel());
            pst.setString(8, product.getCapacity());
            Timestamp time = new Timestamp(product.getDtCreate().getTime());
            pst.setTimestamp(9, time);
            pst.setInt(10, product.getManufacturer().getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            int idProd = 0;
            if(rs.next()){
                idProd = rs.getInt("id_product");
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
            sql.append("UPDATE tb_products SET name=?, description=?, caliber=?, weight=?, action=?, origin=?, model=?, capacity=?");
            // estava faltando um espaço aqui no começo rs
            sql.append(" WHERE id_product=?");
            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, product.getName());
            // settamos o id como um parametro qualquer. Ele é o segundo "?" na query correto? Então, ele será o segundo a ser settado.
            pst.setString(2, product.getDescription());
            pst.setString(3, product.getCaliber());
            pst.setFloat(4, product.getWeight());
            pst.setString(5, product.getAction());
            pst.setString(6, product.getOrigin());
            pst.setString(7, product.getModel());
            pst.setString(8, product.getCapacity());
            pst.setInt(9, product.getId());
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
        
        openConnection();
        PreparedStatement pst = null;
        Product product = (Product) entity;
        List<DomainEntity> products = new ArrayList<>();
        boolean ehEspecifico = false;
        
        try{
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT m.name as fabricante_nome, m.dtCreate as fabricante_dataCadastro, p.* FROM tb_products p INNER JOIN tb_manufacturer m on p.id_manuf = m.id_manufacturer");
            // Estamos buscando um produto especifico?
            if(product.getId() != 0){ // pq != 0? INT é um tipo primitivo. Ou seja, NUNCA será null. Caso esteja 0, é pq não foi informado um ID especifico.
                sql.append(" WHERE id_product=?"); // vamos procurar um produto especifico
                ehEspecifico = true;
            } 
            
            pst = connection.prepareStatement(sql.toString());
            if(ehEspecifico){ // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, product.getId());
            }
            
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while(rs.next()){
                // novo registro, novo product!
                Product p = new Product();
                Manufacturer m = new Manufacturer();
                // pegamos os valores das colunas e settamos no objeto de Product - Se a coluna for int: rs.getInt("NOME DA COLUNA")
                p.setName(rs.getString("name"));
                p.setId(rs.getInt("id_product"));
                p.setDescription(rs.getString("description"));
                p.setCaliber(rs.getString("caliber"));
                p.setWeight(rs.getFloat("weight"));
                p.setAction(rs.getString("action"));
                p.setOrigin(rs.getString("origin"));
                p.setModel(rs.getString("model"));
                p.setCapacity(rs.getString("capacity"));
                java.sql.Date dtCreateLong = rs.getDate("dtCreate");
                m.setName(rs.getString("fabricante_nome"));
                m.setId(rs.getInt("id_manuf"));
                p.setManufacturer(m);
                Date dtCreate = new Date(dtCreateLong.getTime());
                p.setDtCreate(dtCreate);
                // adicionamos o produto na lista, que iremos retornar com todos os valores encontrados...
                products.add(p);
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
        return products;
    }
}
