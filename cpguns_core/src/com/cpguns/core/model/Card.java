package com.cpguns.core.model;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   06/05/2017
 */
public class Card extends DomainEntity{
    
    private String name;
    private String number;
    private String month;
    private String year;
    private String csc;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the csc
     */
    public String getCsc() {
        return csc;
    }

    /**
     * @param csc the csc to set
     */
    public void setCsc(String csc) {
        this.csc = csc;
    }

}
