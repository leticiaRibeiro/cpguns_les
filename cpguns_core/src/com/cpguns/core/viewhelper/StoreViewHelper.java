/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.City;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.State;
import com.cpguns.core.model.Store;
import com.cpguns.core.model.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leticia
 */
public class StoreViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Store store = null;
        Address address = null;
        City city = null;
        State state = null;
        User user = null;
        Gson gson = new Gson();

        if (("CONSULTAR").equals(operacao)) {
            store = new Store();

        } else if (("SALVAR").equals(operacao) || ("ALTERAR").equals(operacao)) {
            String name = request.getParameter("name");
            String street = request.getParameter("address");
            String number = request.getParameter("number");
            String complement = request.getParameter("complement");
            String zip = request.getParameter("zip");
            String neighborhood = request.getParameter("neighborhood");
            String paramCity = request.getParameter("city");
            String paramState = request.getParameter("state");
            String login = request.getParameter("login");
            String senha = request.getParameter("password");

            user = new User();
            store = new Store();
            address = new Address();
            city = new City();
            state = new State();

            if (("ALTERAR").equals(operacao)) {
                store = gson.fromJson(request.getParameter("store"), Store.class);
            } else {
                user.setEmail(login);
                user.setPassword(senha);
                user.setLevel(101);
                store.setUser(user);
                store.setLevel(101);
                store.setName(name);
                address.setStreet(street);
                store.setDtCreate(new Date());
                address.setNumber(number);
                address.setComplement(complement);
                address.setZip(zip);
                address.setNeighborhood(neighborhood);
                city.setName(paramCity);
                state.setName(paramState);
                city.setState(state);
                address.setCity(city);
                store.setAddress(address);
            }

        } else if (("EXCLUIR").equals(operacao)) {
            int id = Integer.valueOf(request.getParameter("id"));
            int id_user = Integer.valueOf(request.getParameter("id_user"));

            store = new Store();
            user = new User();
            user.setId(id_user);
            store.setId(id);
            store.setUser(user);
        }

        return store;
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
