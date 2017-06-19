package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Acesso;
import com.cpguns.core.model.DomainEntity;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Leticia
 */
public class AcessoDAO extends AbstractJdbcDAO{
    
    public AcessoDAO(){
        super("tb_acessos", "id_acesso");
    }

    @Override
    public void create(DomainEntity entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Acesso acesso = (Acesso) entidade;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_acessos(id_prod, dtacesso)");
            sql.append(" VALUES (?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, acesso.getProduct().getId());
            Timestamp timedtCreate = new Timestamp(new Date().getTime());
            pst.setTimestamp(2, timedtCreate);
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

}
