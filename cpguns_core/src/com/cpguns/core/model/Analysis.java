/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class Analysis extends DomainEntity{
    
    private List<AnaliseAcessos> acessos = new ArrayList<>();
    private TipoGrafico grafico;
    private List<AnaliseEstados> estados = new ArrayList<>();
    /*
        -----Query de análise dos acessos-----
        SELECT count(a.id_prod), p.id_product, a.dtacesso
        FROM tb_acessos a INNER JOIN tb_products p on a.id_prod = p.id_product 
        WHERE a.dtacesso between '2017-03-01' and '2017-05-15' group by a.id_prod, p.id_product, a.dtacesso
        ORDER BY p.id_product asc;
    
    */
    
    
    /*
        -- saber da policia
        SELECT count(c.id_costumer)  FROM tb_orders o INNER JOIN tb_order_product op on o.id_order = op.id_order 
        INNER JOIN tb_products p on p.id_product = op.id_product
        INNER JOIN tb_costumers c on c.id_costumer = o.id_cos
        INNER JOIN tb_autorizacoes aut on aut.autorizacao = c.fk_auto
        WHERE o.dtcreate between '2017-05-01' and '2017-05-15' and p.caliber like '.38' and aut.tipo like 'policial' group by c.id_costumer;

        -- saber do civil
        SELECT count(c.id_costumer)  FROM tb_orders o INNER JOIN tb_order_product op on o.id_order = op.id_order 
        INNER JOIN tb_products p on p.id_product = op.id_product
        INNER JOIN tb_costumers c on c.id_costumer = o.id_cos
        INNER JOIN tb_autorizacoes aut on aut.autorizacao = c.fk_auto
        WHERE o.dtcreate between '2017-05-01' and '2017-05-15' and p.caliber like '.38' and aut.tipo like 'pessoafisica' group by c.id_costumer;

    */
    
    
    /*
    
        SELECT count(s.id_store), a.state FROM tb_orders o INNER JOIN tb_stores s on o.id_sto = s.id_store
        INNER JOIN tb_addresses a on s.id_add = a.id_address
        WHERE o.dtcreate between '2017-05-01' and '2017-05-15' group by a.state, s.id_store
    */

    /**
     * @return the acessos
     */
    public List<AnaliseAcessos> getAcessos() {
        return acessos;
    }

    /**
     * @param acessos the acessos to set
     */
    public void setAcessos(List<AnaliseAcessos> acessos) {
        this.acessos = acessos;
    }

    /**
     * @return the grafico
     */
    public TipoGrafico getGrafico() {
        return grafico;
    }

    /**
     * @param grafico the grafico to set
     */
    public void setGrafico(TipoGrafico grafico) {
        this.grafico = grafico;
    }

    /**
     * @return the estados
     */
    public List<AnaliseEstados> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<AnaliseEstados> estados) {
        this.estados = estados;
    }
    
    
}
