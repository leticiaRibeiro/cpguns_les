/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.Carrinho;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Product;
import com.cpguns.core.model.Status;
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
        sql.append("autorizacao TEXT,");
        sql.append("dtCreate date,");
        sql.append("status TEXT); ");

        sql.append("CREATE TABLE tb_order_product(");
        sql.append("id_order INTEGER REFERENCES tb_orders(id_order), ");
        sql.append("id_product INTEGER REFERENCES tb_products(id_product), ");
        sql.append("qtde INTEGER);");

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
            sql.append("INSERT INTO tb_orders(id_sto, id_cos, id_credcard, valortotal, autorizacao, dtcreate, status)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, order.getStore().getId());
            pst.setInt(2, order.getCostumer().getId());
            pst.setInt(3, order.getCard().getId());
            pst.setDouble(4, order.getValorTotal());
            pst.setString(5, order.getAutorizacao());
            Timestamp timedtCreate = new Timestamp(new Date().getTime());
            pst.setTimestamp(6, timedtCreate);
            pst.setString(7, order.getStatus().getDescricao());
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
                    guardarProdutosComprados(order.getId(), p);
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
        openConnection();
        PreparedStatement pst = null;
        ProductDAO pDAO = new ProductDAO();
        CostumerDAO cDAO = new CostumerDAO();
        StoreDAO sDAO = new StoreDAO();
        CardDAO cardDAO = new CardDAO();
        Order order = (Order) entity;
        List<DomainEntity> orders = new ArrayList<>();
        boolean ehPedidoEspecifico = false;
        boolean ehCostumerEspecifico = false;
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_orders o INNER JOIN tb_order_product op on o.id_order=op.id_order ");
            // Estamos buscando um cliente especifico?
            if (order.getCostumer() != null) {
                if (order.getCostumer().getId() != 0) {
                    sql.append("WHERE o.id_cos=?;"); // vamos procurar um cliente especifico
                    ehCostumerEspecifico = true;
                }
            } else if (order.getId() != 0) { // pq != 0? INT é um tipo primitivo. Ou seja, NUNCA será null. 
                // Caso esteja 0, é pq não foi informado um ID especifico.
                sql.append("WHERE o.id_order=?;"); // vamos procurar um cliente especifico
                ehPedidoEspecifico = true;
            }

            pst = connection.prepareStatement(sql.toString());
            if (ehPedidoEspecifico) { // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, order.getId());
            } else if (ehCostumerEspecifico) {
                pst.setInt(1, order.getCostumer().getId());
            }

            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            int id = 0;
            Product p;
            List<Product> products = new ArrayList<>();
            Carrinho carrinho = new Carrinho();
            Order o = new Order();
            Costumer c;
            Store s;
            Card card;
            while (rs.next()) {
                if (rs.getInt("id_order") == id) { // mesmo pedido
                    p = new Product();
                    p.setId(rs.getInt("id_product"));
                    p = (Product) pDAO.read(p).get(0);
                    p.setQtdeCarrinho(rs.getInt("qtde"));
                    products.add(p);
                } else { // novo pedido
                    if (id != 0) {
                        carrinho.setProducts(products);
                        o.setCarrinho(carrinho);
                        orders.add(o);
                    }
                    id = rs.getInt("id_order");
                    c = new Costumer();
                    products = new ArrayList<>();
                    o = new Order();
                    carrinho = new Carrinho();
                    p = new Product();
                    s = new Store();
                    card = new Card();

                    c.setId(rs.getInt("id_cos"));
                    card.setId(rs.getInt("id_credcard"));
                    s.setId(rs.getInt("id_sto"));
                    p.setId(rs.getInt("id_product"));
                    // buscar as FKs
                    o.setCard((Card) cardDAO.read(card).get(0));
                    o.setCostumer((Costumer) cDAO.read(c).get(0));
                    o.setStore((Store) sDAO.read(s).get(0));

                    // settar o primeiro produto
                    p = (Product) pDAO.read(p).get(0);
                    p.setQtdeCarrinho(rs.getInt("qtde"));
                    products.add(p);

                    // settar os dados comuns de uma order
                    o.setId(id);
                    o.setAutorizacao(rs.getString("autorizacao"));
                    o.setValorTotal(rs.getDouble("valortotal"));
                    java.sql.Date dtCreateLong = rs.getDate("dtcreate");
                    Date dtCreate = new Date(dtCreateLong.getTime());
                    o.setDtCreate(dtCreate);
                }
            }
            carrinho.setProducts(products);
            o.setCarrinho(carrinho);
            orders.add(o);

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
        return orders;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {
        
        openConnection();
        PreparedStatement pst = null;
        Order order = (Order) entity;
        
        
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_orders SET status=? ");
            sql.append("WHERE id_order=?");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, order.getStatus().getDescricao());
            pst.setInt(2, order.getId());
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

    private void guardarProdutosComprados(int id_order, Product product) {
        PreparedStatement pst = null;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_order_product(id_order, id_product, qtde)");
            sql.append(" VALUES (?,?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, id_order);
            pst.setInt(2, product.getId());
            pst.setInt(3, product.getQtdeCarrinho());

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
