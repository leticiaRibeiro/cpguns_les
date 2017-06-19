/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Store;
import com.cpguns.core.model.TipoAutorizacao;
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
 * @author Leticia
 */
public class UserDAO extends AbstractJdbcDAO {

    public UserDAO() {
        super("tb_users", "id_user");
    }

    public void createTableUser() {

        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_users(");
        sql.append("email text, ");
        sql.append("password text, ");
        sql.append("level integer, ");
        sql.append("id_user serial primary key, ");
        sql.append("ativo boolean) ");

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

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_users(email, password, level, ativo)");
            sql.append(" VALUES (?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getLevel());
            pst.setBoolean(4, true);
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idUs = 0;
            if (rs.next()) {
                idUs = rs.getInt("id_user");
            }
            user.setId(idUs);
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

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        User user = (User) entity;
        Costumer costumer;
        Store store;
        Autorizacao autorizacao;
        User u = null;
        AddressDAO addressDAO = new AddressDAO();
        List<DomainEntity> retorno = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * from tb_users where email=? and password=? and ativo=true;");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                u = new User();
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setLevel(rs.getInt("level"));
                u.setId(rs.getInt("id_user"));
                u.setAtivo(rs.getBoolean("ativo"));
            }

            if (u != null) {
                boolean achou = false;
                sql.delete(0, sql.length());
                sql.append("SELECT * from tb_costumers c");
                sql.append(" inner join tb_autorizacoes a on a.cpf=c.cpf");
                sql.append(" where c.id_us=?;");
                pst = connection.prepareStatement(sql.toString());
                pst.setInt(1, u.getId());

                rs = pst.executeQuery();

                while (rs.next()) { // achou o user em costumer
                    achou = true;
                    costumer = new Costumer();
                    autorizacao = new Autorizacao();

                    autorizacao.setAutorizacao(rs.getString("autorizacao"));
                    autorizacao.setNivel(rs.getInt("nivel_acesso"));
                    if (rs.getString("tipo").equals(TipoAutorizacao.CIVIL.toString())) {
                        autorizacao.setTipo(TipoAutorizacao.CIVIL);
                    } else {
                        autorizacao.setTipo(TipoAutorizacao.POLICIAL);
                    }

                    costumer.setAutorizacao(autorizacao);
                    costumer.setUser(u);
                    costumer.setId(rs.getInt("id_costumer"));
                    costumer.setName(rs.getString("name"));
                    costumer.setCpf(rs.getString("cpf"));
                    costumer.setRg(rs.getString("rg"));
                    costumer.setGenre(rs.getString("genre"));
                    costumer.setDtBirth(rs.getDate("dt_birth"));
                    costumer.setPhoneNumber(rs.getString("phone_number"));
                    costumer.setDtCreate(rs.getDate("dt_create"));

                    retorno.add(costumer);
                }
                if (!achou) { // não achou o user em costumer, então é uma loja
                    store = new Store();

                    sql.delete(0, sql.length());
                    sql.append("SELECT * from tb_stores where id_user=?");
                    pst = connection.prepareStatement(sql.toString());
                    pst.setInt(1, u.getId());

                    rs = pst.executeQuery();
                    
                    while(rs.next()){
                        Address ad = new Address();
                        ad.setId(rs.getInt("id_add"));
                        store.setUser(u);
                        store.setId(rs.getInt("id_store"));
                        store.setName(rs.getString("name"));
                        store.setLevel(101);
                        store.setAddress((Address) addressDAO.read(ad).get(0));
                        
                        retorno.add(store);
                    }
                }

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
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
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
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(DomainEntity entity) {
        openConnection();
        PreparedStatement pst = null;
        User user = (User) entity;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_users SET ativo=?");
            sql.append(" WHERE id_user=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setBoolean(1, false);
            pst.setInt(2, user.getId());
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
