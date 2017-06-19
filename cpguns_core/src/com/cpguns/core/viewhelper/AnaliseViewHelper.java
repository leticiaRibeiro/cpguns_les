package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Analysis;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.TipoGrafico;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leticia
 */
public class AnaliseViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String tipo = request.getParameter("tipo");
        Analysis analise = new Analysis();

        String inicio = request.getParameter("inicio");
        String fim = request.getParameter("fim");
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dtInicio = sourceFormat.parse(inicio);
            Date dtFim = sourceFormat.parse(fim);
            analise.setDtInicio(dtInicio);
            analise.setDtFim(dtFim);
        } catch (ParseException ex) {
            Logger.getLogger(CostumerViewHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tipo.equals("acessos")) {
            analise.setGrafico(TipoGrafico.ACESSOS);
        } else if (tipo.equals("estados")) {
            analise.setGrafico(TipoGrafico.VENDA_ESTADOS);
        } else if (tipo.equals("vendas")) {
            analise.setGrafico(TipoGrafico.VENDAS_POLICIAIS_CIVIS);
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
