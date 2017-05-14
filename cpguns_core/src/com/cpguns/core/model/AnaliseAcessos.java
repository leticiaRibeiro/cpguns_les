package com.cpguns.core.model;

import java.util.Date;
import java.util.List;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   14/05/2017
 */
public class AnaliseAcessos{
    
    private Date dtAcesso;
    private List<Acesso> acessos;

    /**
     * @return the dtAcesso
     */
    public Date getDtAcesso() {
        return dtAcesso;
    }

    /**
     * @param dtAcesso the dtAcesso to set
     */
    public void setDtAcesso(Date dtAcesso) {
        this.dtAcesso = dtAcesso;
    }

    /**
     * @return the acessos
     */
    public List<Acesso> getAcessos() {
        return acessos;
    }

    /**
     * @param acessos the acessos to set
     */
    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

}
