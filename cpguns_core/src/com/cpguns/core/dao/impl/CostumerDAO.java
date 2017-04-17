/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
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
public class CostumerDAO extends AbstractJdbcDAO {

    public CostumerDAO() {
        super("tb_costumer", "id_costumer");
    }

    public void createTableCostumer() {

        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_costumers(");
        sql.append("id_costumer serial primary key, ");
        sql.append("name text not null, ");
        sql.append("cpf text, ");
        sql.append("rg text, ");
        sql.append("genre text, ");
        sql.append("dt_birth date, ");
        sql.append("phone_number text, ");
        sql.append("id_us INTEGER REFERENCES tb_users(id_user), ");
        sql.append("dt_create date, ");
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

        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;
        UserDAO usDAO = new UserDAO();

        try {

            usDAO.create(costumer.getUser());
            openConnection();
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_costumers(name, cpf, rg, genre, dt_birth, phone_number, id_us, dt_create, ativo)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, costumer.getName());
            pst.setString(2, costumer.getCpf());
            pst.setString(3, costumer.getRg());
            pst.setString(4, costumer.getGenre());
            Timestamp timedtBirth = new Timestamp(costumer.getDtBirth().getTime());
            pst.setTimestamp(5, timedtBirth);
            pst.setString(6, costumer.getPhoneNumber());
            pst.setInt(7, costumer.getUser().getId());
            Timestamp timedtCreate = new Timestamp(costumer.getDtCreate().getTime());
            pst.setTimestamp(8, timedtCreate);
            pst.setBoolean(9, true);
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idCos = 0;
            if (rs.next()) {
                idCos = rs.getInt("id_costumer");
            }
            costumer.setId(idCos);
            connection.commit();

        } catch (Exception e) {

        }
    }

    @Override
    public List<DomainEntity> read(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;
        List<DomainEntity> costumers = new ArrayList<>();
        boolean ehEspecifico = false;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT c.*, u.* FROM tb_costumers c INNER JOIN tb_users u on c.id_us = u.id_user");
            // Estamos buscando um cliente especifico?
            if (costumer.getId() != 0) { // pq != 0? INT é um tipo primitivo. Ou seja, NUNCA será null. 
                // Caso esteja 0, é pq não foi informado um ID especifico.
                sql.append(" WHERE id_costumer=?"); // vamos procurar um cliente especifico
                ehEspecifico = true;
            }

            pst = connection.prepareStatement(sql.toString());
            if (ehEspecifico) { // caso for o especifico, precisamos settar o ID para saber qual é o especifico
                pst.setInt(1, costumer.getId());
            }

            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while (rs.next()) {
                // novo registro, novo product!
                Costumer c = new Costumer();
                User u = new User();
                // pegamos os valores das colunas e settamos no objeto de Product - Se a coluna for int: rs.getInt("NOME DA COLUNA")
                c.setName(rs.getString("name"));
                c.setId(rs.getInt("id_costumer"));
                c.setCpf(rs.getString("cpf"));
                c.setRg(rs.getString("rg"));
                c.setGenre(rs.getString("genre"));
                c.setPhoneNumber(rs.getString("phone_number"));
                java.sql.Date dtBirthLong = rs.getDate("dt_birth");
                Date dtBirth = new Date(dtBirthLong.getTime());
                c.setDtBirth(dtBirth);
                java.sql.Date dtCreateLong = rs.getDate("dt_create");
                Date dtCreate = new Date(dtCreateLong.getTime());
                c.setDtCreate(dtCreate);
                u.setId(rs.getInt("id_us"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setLevel(rs.getInt("level"));
                c.setUser(u);

                // adicionamos o cliente na lista, que iremos retornar com todos os valores encontrados...
                costumers.add(c);
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
        return costumers;
    }

    @Override
    public void update(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;
        UserDAO usDAO = new UserDAO();

        try {
            usDAO.update(costumer.getUser());
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_costumers SET name=?, cpf=?, rg=?, dt_birth=?, genre=?, phone_number=?");
            sql.append(" WHERE id_costumer=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, costumer.getName());
            pst.setString(2, costumer.getCpf());
            pst.setString(3, costumer.getRg());
            Timestamp timedtBirth = new Timestamp(costumer.getDtBirth().getTime());
            pst.setTimestamp(4, timedtBirth);
            pst.setString(5, costumer.getGenre());
            pst.setString(6, costumer.getPhoneNumber());
            pst.setInt(7, costumer.getId());
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
        Costumer costumer = (Costumer) entity;
        UserDAO usDAO = new UserDAO();

        try {
            usDAO.delete(costumer.getUser());
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_costumers SET ativo=?");
            sql.append(" WHERE id_costumer=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setBoolean(1, false);
            pst.setInt(2, costumer.getId());
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

    public boolean verificaExistenciaRg(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_costumers");
            sql.append(" WHERE rg=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, costumer.getRg());
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while (rs.next()) {
                return true;
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
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean verificaExistenciaCpf(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_costumers");
            sql.append(" WHERE cpf=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, costumer.getCpf());
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while (rs.next()) {
                return true;
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
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean verificaExistenciaEmail(DomainEntity entity) throws SQLException {

        openConnection();
        PreparedStatement pst = null;
        Costumer costumer = (Costumer) entity;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_users");
            sql.append(" WHERE email=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, costumer.getUser().getEmail());
            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while (rs.next()) {
                return true;
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
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
