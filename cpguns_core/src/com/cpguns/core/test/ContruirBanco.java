package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AddressDAO;
import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.dao.impl.ManufacturerDAO;
import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.dao.impl.StoreDAO;
import com.cpguns.core.dao.impl.UserDAO;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   16/04/2017
 */
public class ContruirBanco {

    public static void main(String[] args) {
        
        CostumerDAO costumerDAO = new CostumerDAO();
        UserDAO user = new UserDAO();
        AddressDAO addDAO = new AddressDAO();
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        ProductDAO pDAO = new ProductDAO();
        StoreDAO sDAO = new StoreDAO();
        
        manuDAO.createTableManufacturer();
        pDAO.createTableProduct();        
        addDAO.createTableAddress();
        user.createTableUser();
        costumerDAO.createTableCostumer();
        sDAO.createTableStore();
        
    }

}
