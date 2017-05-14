package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Analysis;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.TipoGrafico;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date 14/05/2017
 */
public class AnaliseViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String tipo = request.getParameter("tipo");
        Analysis analise = new Analysis();
        if (tipo.equals("acessos")) {
            analise.setGrafico(TipoGrafico.ACESSOS);
        }

        return analise;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Gson gson = new Gson();
        String retorno;

        retorno = gson.toJson(resultado.getEntidades());
        response.getWriter().write(retorno);
    }

}
