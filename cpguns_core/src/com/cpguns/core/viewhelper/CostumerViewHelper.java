/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class CostumerViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Costumer costumer = null;
        User user;

        if (("CONSULTAR").equals(operacao)) {
            costumer = new Costumer();
            user = new User();
            costumer.setUser(user);
        } else if (("SALVAR").equals(operacao) || ("ALTERAR").equals(operacao)) {
            String name = request.getParameter("name");
            String nascimento = request.getParameter("nascimento");
            String genre = request.getParameter("genre");
            String cpf = request.getParameter("cpf");
            String rg = request.getParameter("rg");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String autorizacao = request.getParameter("autorizacao");
            Autorizacao auto = new Autorizacao();
            auto.setAutorizacao(autorizacao);

            costumer = new Costumer();
            user = new User();

            if (("ALTERAR").equals(operacao)) {
                int id = Integer.valueOf(request.getParameter("id"));
                costumer.setId(id);
                user.setId(id);
            }

            user.setEmail(email);
            user.setPassword(password);
            user.setLevel(1);
            costumer.setUser(user);

            costumer.setAutorizacao(auto);
            costumer.setCpf(cpf);
            costumer.setDtCreate(new Date());
            costumer.setGenre(genre);
            costumer.setName(name);
            costumer.setPhoneNumber(phone);
            costumer.setRg(rg);

            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = sourceFormat.parse(nascimento);
                costumer.setDtBirth(date);
            } catch (ParseException ex) {
                Logger.getLogger(CostumerViewHelper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (("EXCLUIR").equals(operacao)) {
            int id = Integer.valueOf(request.getParameter("id"));

            costumer = new Costumer();
            user = new User();
            user.setId(id);
            costumer.setUser(user);
            costumer.setId(id);
        }

        return costumer;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
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
