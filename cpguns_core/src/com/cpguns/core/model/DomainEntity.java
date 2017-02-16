package com.cpguns.core.model;

import java.util.Date;

public class DomainEntity {
    
    private int id;
    private Date dtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }
}
