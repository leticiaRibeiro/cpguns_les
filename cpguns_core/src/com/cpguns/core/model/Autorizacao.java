/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.model;

/**
 *
 * @author Leticia
 */
public class Autorizacao extends DomainEntity{
    
    private String autorizacao;
    private TipoAutorizacao tipo;

    /**
     * @return the autorizacao
     */
    public String getAutorizacao() {
        return autorizacao;
    }

    /**
     * @param autorizacao the autorizacao to set
     */
    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    /**
     * @return the tipo
     */
    public TipoAutorizacao getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoAutorizacao tipo) {
        this.tipo = tipo;
    }
}
