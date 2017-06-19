package com.cpguns.core.test;

import com.cpguns.core.dao.impl.OrderDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Leticia
 */
public class TestaConsultarOrder {

    public static void main(String[] args) {
        Order o = new Order();
        //o.setId(1);
        OrderDAO oDAO = new OrderDAO();
        
        try {
            List<DomainEntity> read = oDAO.read(o);
            
            for(DomainEntity e : read){
                o = (Order) e;
                System.out.println("Numero do pedido: "+o.getId()+" - "+ o.getStatus().getDescricao()+"("+o.getDtCreate()+")");
                System.out.println("Numero de produtos: "+o.getCarrinho().getProducts().size());
                for(Product p : o.getCarrinho().getProducts()){
                    System.out.println("   - ID:"+p.getId()+" , name: "+p.getName()+", price:"+ p.getPrice()+", qtde: "+p.getQtdeCarrinho());
                }
                System.out.println("Valor total: "+o.getValorTotal());
                System.out.println("Loja de entrega: "+o.getStore().getId()+" - "+o.getStore().getName());
                System.out.println("Cartão utilizado: "+o.getCard().getNumber());
                System.out.println("Usuario: "+o.getCostumer().getId()+" - "+o.getCostumer().getName()+"("+o.getCostumer().getUser().getEmail()+")");
                System.out.println("-----------------------------------------------\n");
            }
            System.out.println("Quantidade: "+read.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestaConsultarOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
