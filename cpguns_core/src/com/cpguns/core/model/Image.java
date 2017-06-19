package com.cpguns.core.model;

/**
 * 
 * @author Leticia
 */
public class Image extends DomainEntity {
    
    private String uri;
    private int id_product;

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the id_product
     */
    public int getId_product() {
        return id_product;
    }

    /**
     * @param id_product the id_product to set
     */
    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

}
