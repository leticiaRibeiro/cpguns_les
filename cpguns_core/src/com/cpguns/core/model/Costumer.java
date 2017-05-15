/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.model;

import java.util.Date;

/**
 *
 * @author Leticia
 */
public class Costumer extends Person{
    
    private String cpf;
    private String rg;
    private String genre;
    private Date dtBirth;
    private String phoneNumber;
    private Autorizacao autorizacao;

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the dtBirth
     */
    public Date getDtBirth() {
        return dtBirth;
    }

    /**
     * @param dtBirth the dtBirth to set
     */
    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
//    public enum Status{
//        ACCEPTED, REJECTED, ANALYSING;
//    }

    /**
     * @return the autorizacao
     */
    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    /**
     * @param autorizacao the autorizacao to set
     */
    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }
    
}
