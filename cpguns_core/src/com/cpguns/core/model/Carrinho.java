package com.cpguns.core.model;

import java.util.List;

/**
 * 
 * @author Leticia
 */
public class Carrinho extends DomainEntity{
    
    private List<Product> products;
    private double total_price;

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return the total_price
     */
    public double getTotal_price() {
        return total_price;
    }

    /**
     * @param total_price the total_price to set
     */
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

}
