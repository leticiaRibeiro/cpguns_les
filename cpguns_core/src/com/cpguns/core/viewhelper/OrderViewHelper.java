package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Product;
import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.Carrinho;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Status;
import com.cpguns.core.model.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Leticia
 */
public class OrderViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        List<Product> products = new ArrayList<>();
        Costumer costumer = new Costumer();
        Store store = new Store();
        Product p;
        Product p2;
        ProductDAO productDAO = new ProductDAO();
        Order order = new Order();
        Card card = new Card();
        Carrinho carrinho = new Carrinho();
        

        if (("SALVAR").equals(operacao)) {
            String json = request.getParameter("carrinho");
            costumer = gson.fromJson(request.getParameter("user"), Costumer.class);
            card = gson.fromJson(request.getParameter("card"), Card.class);
            store = gson.fromJson(request.getParameter("store"), Store.class);
            JSONArray ja = new JSONArray(json);
            for (int i = 0; i < ja.length(); i++) {
                p = new Product();

                JSONObject jsonObj = ja.getJSONObject(i);
                p.setId(jsonObj.getInt("id"));
                try {
                    p = (Product) productDAO.read(p).get(0);
                    p.setQtdeCarrinho(jsonObj.getInt("qtdeCarrinho"));
                    products.add(p);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            carrinho.setProducts(products);
            order.setCarrinho(carrinho);
            order.setCostumer(costumer);
            order.setStore(store);
            order.setCard(card);
            order.setAutorizacao(request.getParameter("autorizacao"));
            order.setValorTotal(Double.valueOf(request.getParameter("valorTotal")));
            System.out.println("Oi");
        }
        
        else if (("ALTERAR").equals(operacao)){
            String status = request.getParameter("status");
            if(Status.AGUARDANDO_APROVACAO.getDescricao().equals(status)){
                order.setStatus(Status.AGUARDANDO_APROVACAO);
            } else if (Status.EM_NEGOCIACAO.getDescricao().equals(status)){
                order.setStatus(Status.EM_NEGOCIACAO);
            } else if (Status.EM_TRANSPORTE.getDescricao().equals(status)){
                order.setStatus(Status.EM_TRANSPORTE);
            } else if (Status.CANCELADO.getDescricao().equals(status)){
                order.setStatus(Status.CANCELADO);
            } else if (Status.RETIRADO.getDescricao().equals(status)){
                order.setStatus(Status.RETIRADO);
            } else if (Status.DEVOLVIDO.getDescricao().equals(status)){
                order.setStatus(Status.DEVOLVIDO);
            }
        }
        
        else if (("CONSULTAR").equals(operacao)){
            order = new Order();
        }
        return order;
    }

    @Override
    public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");
        Gson gson = new Gson();
        String retorno = "";
        
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
