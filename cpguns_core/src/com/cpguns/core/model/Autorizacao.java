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
}
