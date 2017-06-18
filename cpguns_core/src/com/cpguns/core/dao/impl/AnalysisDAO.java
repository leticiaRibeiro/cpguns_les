/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.model.Acesso;
import com.cpguns.core.model.AnaliseAcessos;
import com.cpguns.core.model.AnaliseEstados;
import com.cpguns.core.model.AnaliseVendas;
import com.cpguns.core.model.Analysis;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import com.cpguns.core.model.TipoGrafico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class AnalysisDAO extends AbstractJdbcDAO {

    public AnalysisDAO() {
        super("tb_acessos", "id_acesso");
    }

    public void createTableAcesso() {
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_acessos(");
        sql.append("id_acesso serial primary key, ");
        sql.append("id_prod INTEGER REFERENCES tb_products(id_product), ");
        sql.append("tipo text, ");
        sql.append("dtAcesso date); ");

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

    }

    @Override
    public List<DomainEntity> read(DomainEntity entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Analysis analise = (Analysis) entidade;
        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            if (analise.getGrafico() == TipoGrafico.ACESSOS) {
                return analiseAcessos(pst);
            } else if (analise.getGrafico() == TipoGrafico.VENDAS_POLICIAIS_CIVIS) {
                return analiseVendas(pst);
            } else if (analise.getGrafico() == TipoGrafico.VENDA_ESTADOS) {
                return analiseEstados(pst);
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
        return null;
    }

    @Override
    public void update(DomainEntity entidade) throws SQLException {

    }

    private List<DomainEntity> analiseAcessos(PreparedStatement pst) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Analysis analise = new Analysis();
        analise.setGrafico(TipoGrafico.ACESSOS);
        List<DomainEntity> retorno = new ArrayList<>();
        AnaliseAcessos analiseAcessos = new AnaliseAcessos();
        List<Acesso> acessos = new ArrayList<>();
        List<AnaliseAcessos> listaFinal = new ArrayList<>();
        connection.setAutoCommit(false);
        sql.append("SELECT a.dtacesso\n"
                + "        FROM tb_acessos a INNER JOIN tb_products p on a.id_prod = p.id_product\n"
                + "        WHERE a.dtacesso between '2017-03-01' and '2017-05-15' group by a.dtacesso\n"
                + "        ORDER BY a.dtacesso asc;");
        pst = connection.prepareStatement(sql.toString());

        ResultSet rs = pst.executeQuery();
        // enquanto houver registros, vamos lendo....

        while (rs.next()) {
            analiseAcessos = new AnaliseAcessos();
            analiseAcessos.setDtAcesso(rs.getDate("dtacesso"));
            listaFinal.add(analiseAcessos);
        }

        sql.delete(0, sql.length());
        sql.append("SELECT count(a.id_prod), p.id_product, a.dtacesso\n"
                + "        FROM tb_acessos a INNER JOIN tb_products p on a.id_prod = p.id_product\n"
                + "        WHERE a.dtacesso between '2017-03-01' and '2017-05-15' group by a.id_prod, p.id_product, a.dtacesso\n"
                + "        ORDER BY a.dtacesso, p.id_product asc");
        pst = connection.prepareStatement(sql.toString());
        rs = pst.executeQuery();
        int contador = 0;
        while (rs.next()) {
            Acesso acesso = new Acesso();
            Product p = new Product();
            p.setId(rs.getInt("id_product"));
            p = (Product) new ProductDAO().read(p).get(0);
            acesso.setNumeroAcessos(rs.getInt("count"));
            acesso.setProduct(p);
            Date date = rs.getDate("dtacesso");
            if (listaFinal.get(contador).getDtAcesso().getTime() == date.getTime()) {
                acessos.add(acesso);
            } else {
                listaFinal.get(contador).setAcessos(acessos);
                contador++;
                acessos = new ArrayList<>();
                acessos.add(acesso);
            }
        }
        listaFinal.get(contador).setAcessos(acessos);
        analise.setAcessos(listaFinal);
        retorno.add(analise);
        return retorno;
    }

    private List<DomainEntity> analiseVendas(PreparedStatement pst) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Analysis analise = new Analysis();
        analise.setGrafico(TipoGrafico.VENDAS_POLICIAIS_CIVIS);
        List<DomainEntity> retorno = new ArrayList<>();
        AnaliseVendas analiseVendas = new AnaliseVendas();
        List<Acesso> acessos = new ArrayList<>();
        List<AnaliseVendas> listaFinal = new ArrayList<>();
        connection.setAutoCommit(false);
        sql.append("SELECT count(c.id_costumer) , aut.tipo, o.dtcreate FROM tb_orders o INNER JOIN tb_order_product op on o.id_order = op.id_order\n"
                + "        INNER JOIN tb_products p on p.id_product = op.id_product\n"
                + "        INNER JOIN tb_costumers c on c.id_costumer = o.id_cos\n"
                + "        INNER JOIN tb_autorizacoes aut on aut.autorizacao = c.fk_auto\n"
                + "        WHERE o.dtcreate between '2017-05-01' and '2017-05-15' and p.caliber like '.38' group by c.id_costumer, aut.tipo, o.dtcreate;");
        pst = connection.prepareStatement(sql.toString());

        ResultSet rs = pst.executeQuery();
        // enquanto houver registros, vamos lendo....
        
        analiseVendas = new AnaliseVendas();
        while (rs.next()) {
            if (analiseVendas.getDtVenda() != null) {
                if (analiseVendas.getDtVenda().getTime() == rs.getDate("dtcreate").getTime()) {
                    if (rs.getString("tipo").equals("POLICIAL")) {
                        analiseVendas.setPolicial(rs.getInt("count"));
                    } else {
                        analiseVendas.setCivil(rs.getInt("count"));
                    }
                } else {
                    listaFinal.add(analiseVendas);
                    analiseVendas = new AnaliseVendas();
                    analiseVendas.setDtVenda(rs.getDate("dtcreate"));
                    if (rs.getString("tipo").equals("POLICIAL")) {
                        analiseVendas.setPolicial(rs.getInt("count"));
                    } else {
                        analiseVendas.setCivil(rs.getInt("count"));
                    }
                }
            } else {
                analiseVendas.setDtVenda(rs.getDate("dtcreate"));
                if (rs.getString("tipo").equals("POLICIAL")) {
                    analiseVendas.setPolicial(rs.getInt("count"));
                } else {
                    analiseVendas.setCivil(rs.getInt("count"));
                }
            }
        }       

        listaFinal.add(analiseVendas);
        analise.setVendas(listaFinal);
        retorno.add(analise);
        return retorno;
    }

    private List<DomainEntity> analiseEstados(PreparedStatement pst) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Analysis analise = new Analysis();
        analise.setGrafico(TipoGrafico.VENDA_ESTADOS);
        List<DomainEntity> retorno = new ArrayList<>();
        AnaliseEstados analiseEstados = new AnaliseEstados();
        List<AnaliseEstados> listaFinal = new ArrayList<>();
        connection.setAutoCommit(false);
        sql.append("SELECT count(s.id_store), a.state FROM tb_orders o INNER JOIN tb_stores s on o.id_sto = s.id_store\n"
                + "        INNER JOIN tb_addresses a on s.id_add = a.id_address\n"
                + "        WHERE o.dtcreate between '2017-05-01' and '2017-05-15' group by a.state");
        pst = connection.prepareStatement(sql.toString());

        ResultSet rs = pst.executeQuery();
        // enquanto houver registros, vamos lendo....

        while (rs.next()) {
            analiseEstados = new AnaliseEstados();
            analiseEstados.setEstado(rs.getString("state"));
            analiseEstados.setQuantidade(rs.getInt("count"));
            listaFinal.add(analiseEstados);
        }

        analise.setEstados(listaFinal);
        retorno.add(analise);
        return retorno;
    }

}
