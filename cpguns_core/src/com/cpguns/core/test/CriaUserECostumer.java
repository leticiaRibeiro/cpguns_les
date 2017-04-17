package com.cpguns.core.test;

import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.dao.impl.UserDAO;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   16/04/2017
 */
public class CriaUserECostumer {

    public static void main(String[] args) {
        
        CostumerDAO costumerDAO = new CostumerDAO();
        UserDAO user = new UserDAO();
        
        user.createTableUser();
        costumerDAO.createTableCostumer();
        
    }

}
