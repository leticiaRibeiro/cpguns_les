/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.test;

import com.cpguns.core.dao.impl.UserDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class MainUser {
    
    public static void main(String[] args){
     
        User usuario = new User("gustavo@outlook.com", "leribeiro", 1);
        usuario.setId(10);
        UserDAO uDAO = new UserDAO();
        uDAO.createTableUser();
        try{
//            uDAO.update(usuario);
//            List<DomainEntity> listaUser = new ArrayList<>();
//            listaUser = uDAO.read(usuario);
//            
//            for (DomainEntity e : listaUser) {
//                User u = (User) e;
//                System.out.println(u.getEmail());
//                System.out.println(u.getPassword());
//                System.out.println(u.getLevel());
//            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
