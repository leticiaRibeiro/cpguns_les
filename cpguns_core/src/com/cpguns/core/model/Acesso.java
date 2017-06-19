package com.cpguns.core.model;

/**
 * 
 * @author Leticia
 */
public class Acesso extends DomainEntity{
    
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
