package com.cpguns.core.viewhelper;

import com.cpguns.core.app.Result;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Image;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date 03/05/2017
 */
public class ArmsViewHelper implements IViewHelper {

    @Override
    public DomainEntity getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Product p = null;
        if (("CONSULTAR").equals(operacao)) {
            p = new Product();
        } else if (("SALVAR").equals(operacao)) {
            p = new Product();
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(request.getParameter("manufacturer"));
            manufacturer.setDtCreate(new Date());
            manufacturer.setAtivo(true);
            p.setManufacturer(manufacturer);

            List<Image> images = new ArrayList<>();
            Image image = new Image();
            image.setUri(request.getParameter("uri"));
            images.add(image);
            
            p.setImages(images);
            p.setName(request.getParameter("nome"));
            p.setOrigin(request.getParameter("origem"));
            p.setPrice(Double.valueOf(request.getParameter("preco")));
            p.setQtde(Integer.valueOf(request.getParameter("qtde")));
            p.setCaliber(request.getParameter("calibre"));
            p.setWeight(Float.valueOf(request.getParameter("peso")));
            p.setCapacity(request.getParameter("capacidade"));
            p.setModel(request.getParameter("modelo"));
            p.setAction(request.getParameter("action"));
            p.setDescription(request.getParameter("descricao"));
            p.setDtCreate(new Date());
            p.setAtivo(true);
        }

        return p;
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
