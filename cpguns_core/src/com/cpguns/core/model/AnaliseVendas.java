package com.cpguns.core.model;

import java.util.Date;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   15/05/2017
 */
public class AnaliseVendas {
    
    private int civil;
    private int policial;
    private Date dtVenda;

    /**
     * @return the civil
     */
    public int getCivil() {
        return civil;
    }

    /**
     * @param civil the civil to set
     */
    public void setCivil(int civil) {
        this.civil = civil;
    }

    /**
     * @return the policial
     */
    public int getPolicial() {
        return policial;
    }

    /**
     * @param policial the policial to set
     */
    public void setPolicial(int policial) {
        this.policial = policial;
    }

    /**
     * @return the dtVenda
     */
    public Date getDtVenda() {
        return dtVenda;
    }

    /**
     * @param dtVenda the dtVenda to set
     */
    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

}
