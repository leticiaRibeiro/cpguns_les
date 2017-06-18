
package com.cpguns.core.model;

import java.util.List;

/**
 *
 * @author Leticia
 */
public class Product extends DomainEntity{
    
    private String name;
    private String description;
    private String caliber;
    private float weight;
    private String action;
    private String origin;
    private String model;
    private String capacity;                // measured in rd.
    private Double price;
    private Manufacturer manufacturer;
    private List<Image> images;
    private int qtde;
    private int qtdeCarrinho;
    private int nivelAcesso;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaliber() {
        return caliber;
    }
    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the qtde
     */
    public int getQtde() {
        return qtde;
    }

    /**
     * @param qtde the qtde to set
     */
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    /**
     * @return the qtdeCarrinho
     */
    public int getQtdeCarrinho() {
        return qtdeCarrinho;
    }

    /**
     * @param qtdeCarrinho the qtdeCarrinho to set
     */
    public void setQtdeCarrinho(int qtdeCarrinho) {
        this.qtdeCarrinho = qtdeCarrinho;
    }

    /**
     * @return the nivelAcesso
     */
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    /**
     * @param nivelAcesso the nivelAcesso to set
     */
    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    
}
