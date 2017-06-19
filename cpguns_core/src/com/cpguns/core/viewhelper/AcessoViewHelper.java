package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Acesso;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Leticia
 */
public class AcessoViewHelper implements IViewHelper{

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String id_produto = request.getParameter("id_produto");
        Acesso acesso = new Acesso();
        Product produto = new Product();
        produto.setId(Integer.valueOf(id_produto));
        acesso.setProduct(produto);
        
        return acesso;        
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }

}
