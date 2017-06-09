/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.OrderDAO;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Status;
import java.sql.SQLException;

/**
 *
 * @author Leticia
 */
public class MainStatus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        Order o = new Order();
        o.setId(1);
        o.setStatus(Status.AGUARDANDO_APROVACAO);

        orderDAO.update(o);
    }
    
}
