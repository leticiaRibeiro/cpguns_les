package com.cpguns.core.test;

import com.cpguns.core.dao.impl.ManufacturerDAO;
import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.Image;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date   03/05/2017
 */
public class AddArms {

    public static void main(String[] args) {
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        ProductDAO pDAO = new ProductDAO();
        
        Manufacturer m1 = new Manufacturer();
        m1.setName("Russia");
        m1.setAtivo(true);
        m1.setDtCreate(new Date());
        
        Manufacturer m2 = new Manufacturer();
        m2.setName("USA");
        m2.setAtivo(true);
        m2.setDtCreate(new Date());
        
        Manufacturer m3 = new Manufacturer();
        m3.setName("Brasil");
        m3.setAtivo(true);
        m3.setDtCreate(new Date());
        
        try {
            manuDAO.create(m1);
            manuDAO.create(m2);
            manuDAO.create(m3);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Product p1 = new Product();
        List<Image> images1 = new ArrayList<>();
        Image i1 = new Image();
        i1.setUri("../images/ak47.png");
        images1.add(i1);
        
        p1.setImages(images1);
        p1.setName("AK 47");
        p1.setManufacturer(m1);
        p1.setOrigin("Afeganistao");
        p1.setWeight(23);
        p1.setModel("N/A");
        p1.setDtCreate(new Date());
        p1.setDescription("Arma tema dos terroristas");
        p1.setCapacity("31");
        p1.setCaliber(".38");
        p1.setPrice(3000.00);
        p1.setAtivo(true);
        p1.setAction("N/A");
        
        
        
        Product p2 = new Product();
        List<Image> images2 = new ArrayList<>();
        Image i2 = new Image();
        i2.setUri("../images/m4a4.png");
        images2.add(i2);
        
        p2.setImages(images2);
        p2.setName("M4A4");
        p2.setManufacturer(m2);
        p2.setOrigin("USA");
        p2.setWeight(27);
        p2.setModel("N/A");
        p2.setDtCreate(new Date());
        p2.setDescription("Arma tema dos contra-terroristas");
        p2.setCapacity("31");
        p2.setCaliber(".38");
        p2.setPrice(4500.00);
        p2.setAtivo(true);
        p2.setAction("N/A");
        
        
        
        Product p3 = new Product();
        List<Image> images3 = new ArrayList<>();
        Image i3 = new Image();
        i3.setUri("../images/glock.png");
        images3.add(i3);
        
        p3.setImages(images3);
        p3.setName("Glock");
        p3.setManufacturer(m3);
        p3.setOrigin("Brasil");
        p3.setWeight(27);
        p3.setModel("N/A");
        p3.setDtCreate(new Date());
        p3.setDescription("Pistola que nunca da HS");
        p3.setCapacity("31");
        p3.setCaliber(".38");
        p3.setPrice(1300.00);
        p3.setAtivo(true);
        p3.setAction("N/A");

        try {        
            pDAO.create(p1);
            pDAO.create(p2);
            pDAO.create(p3);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
