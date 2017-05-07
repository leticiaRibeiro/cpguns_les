/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class OrderDAO extends AbstractJdbcDAO {

    public OrderDAO() {
        super("tb_orders", "id_order");
    }

    public void createTableOrder() {
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

        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(DomainEntity entity) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        CardDAO cardDAO = new CardDAO();
        Order order = (Order) entity;

        try {
            cardDAO.create(order.getCard());

            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_orders(id_sto, id_cos, id_credcard, valortotal, dtcreate)");
            sql.append(" VALUES (?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, order.getStore().getId());
            pst.setInt(2, order.getCostumer().getId());
            pst.setInt(3, order.getCard().getId());
            pst.setDouble(4, order.getValorTotal());
            Timestamp timedtCreate = new Timestamp(new Date().getTime());
            pst.setTimestamp(5, timedtCreate);
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idOrder = 0;
            if (rs.next()) {
                idOrder = rs.getInt("id_order");
            }
            order.setId(idOrder);
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {

                for (Product p : order.getCarrinho().getProducts()) {
                    guardarProdutosComprados(order.getId(), p.getId());
                }
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {

        return null;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {

    }

    private void guardarProdutosComprados(int id_order, int id_product) {
        PreparedStatement pst = null;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_order_product(id_order, id_product)");
            sql.append(" VALUES (?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, id_order);
            pst.setInt(2, id_product);

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

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
