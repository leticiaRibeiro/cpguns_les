package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.TipoAutorizacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Leticia
 */
public class AutorizacaoDAO extends AbstractJdbcDAO {

    public AutorizacaoDAO() {
        super("tb_autorizacao", "autorizacao");
    }

    public void createTableAutorizacao() {
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_autorizacoes(");
        sql.append("autorizacao text primary key, ");
        sql.append("cpf text, ");
        sql.append("nivel_acesso INTEGER, ");
        sql.append("tipo text); ");

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
    public void create(DomainEntity entidade) throws SQLException {
        PreparedStatement pst = null;
        Autorizacao autorizacao = (Autorizacao) entidade;
        UserDAO usDAO = new UserDAO();

        try {
            openConnection();
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_autorizacoes(autorizacao, cpf, nivel_acesso, tipo)");
            sql.append(" VALUES (?,?,?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, autorizacao.getAutorizacao());
            pst.setString(2, autorizacao.getCpf());
            pst.setInt(3, autorizacao.getNivel());
            pst.setString(4, autorizacao.getTipo().toString());

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
    public List<DomainEntity> read(DomainEntity entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(DomainEntity entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Autorizacao verificaAutorizacao(Costumer costumer) {
        PreparedStatement pst = null;
        Autorizacao autorizacao = null;

        try {
            openConnection();
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_autorizacoes WHERE cpf=? and autorizacao=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, costumer.getCpf());
            pst.setString(2, costumer.getAutorizacao().getAutorizacao());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                autorizacao = new Autorizacao();
                autorizacao.setAutorizacao(rs.getString("autorizacao"));
                autorizacao.setCpf(rs.getString("cpf"));
                autorizacao.setNivel(rs.getInt("nivel_acesso"));
                if(rs.getString("tipo").equals(TipoAutorizacao.CIVIL.toString())){
                    autorizacao.setTipo(TipoAutorizacao.CIVIL);
                } else{
                    autorizacao.setTipo(TipoAutorizacao.POLICIAL);
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
        return autorizacao;
    }

}
