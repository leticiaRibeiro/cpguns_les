package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   06/05/2017
 */
public class OrderViewHelper implements IViewHelper{

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        return null;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
