package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   03/05/2017
 */
public class ArmsViewHelper implements IViewHelper{

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Product p = null;
        if(("CONSULTAR").equals(operacao)){
            p = new Product();
        }
        
        return p;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        Gson gson = new Gson();
        String retorno;
        
        if (("CONSULTAR").equals(operacao)) {
            retorno = gson.toJson(resultado.getEntidades());
            response.getWriter().write(retorno);
        } else if (("SALVAR").equals(operacao)) {
            response.getWriter().write(new Gson().toJson(resultado));
        } else if (("EXCLUIR").equals(operacao)) {
            response.getWriter().write(new Gson().toJson(resultado));
        } else if (("ALTERAR").equals(operacao)) {
            response.getWriter().write(new Gson().toJson(resultado));
        }
    }

}
