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
public class Order extends DomainEntity{
    
    private Carrinho carrinho;
    private Store store;
    private Costumer costumer;
    private double valorTotal;
    private Card card;
    private String autorizacao;

    /**
     * @return the carrinho
     */
    public Carrinho getCarrinho() {
        return carrinho;
    }

    /**
     * @param carrinho the carrinho to set
     */
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @return the costumer
     */
    public Costumer getCostumer() {
        return costumer;
    }

    /**
     * @param costumer the costumer to set
     */
    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(Card card) {
        this.card = card;
    }

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
