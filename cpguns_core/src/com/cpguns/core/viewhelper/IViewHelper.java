/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo
 */
public interface IViewHelper {
    
    public DomainEntity getEntidade(HttpServletRequest request);

    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    
}
