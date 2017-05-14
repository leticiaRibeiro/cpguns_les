/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AnalysisDAO;
import com.cpguns.core.model.Analysis;

/**
 *
 * @author Leticia
 */
public class MainAnalise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AnalysisDAO aDAO = new AnalysisDAO();
        Analysis an = new Analysis();
        
        aDAO.createTableAcesso();
        aDAO.createTableAutorizacao();
        
    }
    
}
