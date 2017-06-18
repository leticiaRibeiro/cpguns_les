package com.cpguns.core.strategy;

import com.cpguns.core.dao.impl.AutorizacaoDAO;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;

/**
 * 
 * @author Leticia
 */
public class VerificaAutorizacao implements IStrategy{

    @Override
    public String process(DomainEntity entity) {
        Costumer costumer = (Costumer) entity;
        AutorizacaoDAO autorizacaoDAO = new AutorizacaoDAO();
        Autorizacao autorizacao = autorizacaoDAO.verificaAutorizacao(costumer);
        if(autorizacao != null){
            costumer.setAutorizacao(autorizacao);
            costumer.getUser().setLevel(autorizacao.getNivel());
            return null;
        } else{
            return "Autorizacao não condiz com o CPF";
        }
    }

}
