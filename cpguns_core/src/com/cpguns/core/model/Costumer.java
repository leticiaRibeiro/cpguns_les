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
public class Costumer extends User{
    
    private String cpf;
    private String rg;
    private String sex;
    private Date dtBirth;
    private String phoneNumber;
    
    public enum Status{
        ACCEPTED, REJECTED, ANALYSING;
    }
    
}
