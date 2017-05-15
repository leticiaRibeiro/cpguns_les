package com.cpguns.core.model;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   14/05/2017
 */
public class Acesso {
    
    private int numeroAcessos;
    private Product product;

    /**
     * @return the numeroAcessos
     */
    public int getNumeroAcessos() {
        return numeroAcessos;
    }

    /**
     * @param numeroAcessos the numeroAcessos to set
     */
    public void setNumeroAcessos(int numeroAcessos) {
        this.numeroAcessos = numeroAcessos;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

}