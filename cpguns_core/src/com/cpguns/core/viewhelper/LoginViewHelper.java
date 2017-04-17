/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo
 */
public class LoginViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        User user = null;

        if (("CONSULTAR").equals(operacao)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            user = new User(email, password);
        }

        return user;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        Gson gson = new Gson();
        String retorno;

        if (("CONSULTAR").equals(operacao)) {
            if (!resultado.getEntidades().isEmpty()) {
                retorno = gson.toJson(resultado.getEntidades());
            } else {
                retorno = null;
            }
            response.getWriter().write(retorno);
        }
    }

}
