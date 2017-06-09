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
public enum Status {
    
    
    AGUARDANDO_APROVACAO("Aguardando Aprovação",1),
    EM_NEGOCIACAO("Em negociação",2),
    CANCELADO("Cancelado",3),
    EM_TRANSPORTE("Em transporte",3),
    RETIRADO("Retirado",4),
    DEVOLVIDO("Devolvido",5);
    
    private int codigo;
    private String descricao;

    Status(String descricao, int codigo){
        this.descricao = descricao;
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }


    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
}
