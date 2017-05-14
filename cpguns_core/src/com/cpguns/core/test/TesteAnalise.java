package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AnalysisDAO;
import com.cpguns.core.model.Analysis;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.TipoGrafico;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   14/05/2017
 */
public class TesteAnalise {

    public static void main(String[] args) {
        Analysis analise = new Analysis();
        AnalysisDAO aDAO = new AnalysisDAO();
        
        analise.setGrafico(TipoGrafico.ACESSOS);
        try {
            List<DomainEntity> read = aDAO.read(analise);
            System.out.println("Quantidade: "+read.size());
        } catch (SQLException ex) {
            Logger.getLogger(TesteAnalise.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

}
