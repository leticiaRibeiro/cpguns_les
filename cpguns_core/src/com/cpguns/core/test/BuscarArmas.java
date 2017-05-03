package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Image;
import com.cpguns.core.model.Product;
import java.sql.SQLException;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   03/05/2017
 */
public class BuscarArmas {

    public static void main(String[] args) throws SQLException {
        
        ProductDAO pDAO = new ProductDAO();
        String msg="";
        
        for(DomainEntity e : pDAO.read(new Product())){
            Product p = (Product) e;
            for(Image i : p.getImages()){
                msg += i.getUri();
            }
            
            System.out.println("ID: "+p.getId()+"\n"
                    + "Nome: "+p.getName()+"\n"
                            + "Imagem: "+msg);
            msg = "";
            System.out.println("-------------------------------------");            
                    
        }
        
    }

}
