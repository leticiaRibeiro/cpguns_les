package com.cpguns.core.dao.impl;

import com.cpguns.core.dao.AbstractJdbcDAO;
import com.cpguns.core.dao.IDAO;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Image;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
import static com.cpguns.core.util.MyConnection.openConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gustavo de Souza Bezerra <gustavo.bezerra@hotmail.com>
 * @date 03/05/2017
 */
public class ImageDAO extends AbstractJdbcDAO {

    public ImageDAO() {
        super("tb_images", "id_image");
    }

    public void createTableProduct(){
        openConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE tb_images(");
        sql.append("id_image serial primary key, ");
        sql.append("uri text not null, ");
        sql.append("id_product INTEGER REFERENCES tb_products(id_product) ");
        sql.append(") ");

        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(DomainEntity entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Image image = (Image) entidade;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_images(uri, id_product)");
            sql.append(" VALUES (?,?)");

            pst = connection.prepareStatement(sql.toString(),
            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, image.getUri());
            pst.setInt(2, image.getId_product());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idImg = 0;
            if(rs.next()){
                idImg = rs.getInt("id_image");
            }
            image.setId(idImg);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try{
                pst.close();
                connection.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<DomainEntity> read(DomainEntity entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Product product = (Product) entidade;
        List<DomainEntity> images = new ArrayList<>();
        boolean ehEspecifico = false;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tb_images WHERE id_product=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, product.getId());

            ResultSet rs = pst.executeQuery();
            // enquanto houver registros, vamos lendo....
            while (rs.next()) {
                // novo registro, nova imagem!
                Image i = new Image();
                i.setUri(rs.getString("uri"));
                i.setId(rs.getInt("id_image"));
                i.setId_product(rs.getInt("id_product"));

                images.add(i);
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    @Override
    public void update(DomainEntity entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
