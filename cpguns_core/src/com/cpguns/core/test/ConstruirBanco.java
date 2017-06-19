package com.cpguns.core.test;

import com.cpguns.core.dao.impl.AddressDAO;
import com.cpguns.core.dao.impl.AnalysisDAO;
import com.cpguns.core.dao.impl.AutorizacaoDAO;
import com.cpguns.core.dao.impl.CardDAO;
import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.dao.impl.ImageDAO;
import com.cpguns.core.dao.impl.ManufacturerDAO;
import com.cpguns.core.dao.impl.OrderDAO;
import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.dao.impl.StoreDAO;
import com.cpguns.core.dao.impl.UserDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.Autorizacao;
import com.cpguns.core.model.Card;
import com.cpguns.core.model.Carrinho;
import com.cpguns.core.model.City;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.Image;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Order;
import com.cpguns.core.model.Product;
import com.cpguns.core.model.State;
import com.cpguns.core.model.Status;
import com.cpguns.core.model.Store;
import com.cpguns.core.model.TipoAutorizacao;
import com.cpguns.core.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Leticia
 */
public class ConstruirBanco {
    public static void main(String[] args) throws SQLException {
        
        CostumerDAO costumerDAO = new CostumerDAO();
        UserDAO user = new UserDAO();
        AddressDAO addDAO = new AddressDAO();
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        ProductDAO pDAO = new ProductDAO();
        StoreDAO sDAO = new StoreDAO();
        ImageDAO imgDAO = new ImageDAO();
        CardDAO cardDAO = new CardDAO();
        OrderDAO oDAO = new OrderDAO();
        AnalysisDAO analiseDAO = new AnalysisDAO();
        AutorizacaoDAO autorizacaoDAO = new AutorizacaoDAO();
        
        autorizacaoDAO.createTableAutorizacao();
        manuDAO.createTableManufacturer();
        pDAO.createTableProduct();
        imgDAO.createTableProduct();
        addDAO.createTableAddress();
        user.createTableUser();
        costumerDAO.createTableCostumer();
        sDAO.createTableStore();
        cardDAO.createTableCard();
        oDAO.createTableOrder();
        analiseDAO.createTableAcesso();
        
        popularAutorizacao(autorizacaoDAO);
        popularArmas();
        popularLojas();
        popularUsuario();
//        fazerPedidoTeste();
    }
    
    public static void popularArmas(){
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
        p1.setQtde(20);
        p1.setNivelAcesso(3);
        
        
        
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
        p2.setQtde(10);
        p2.setNivelAcesso(2);
        
        
        
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
        p3.setQtde(30);
        p3.setNivelAcesso(1);

        try {        
            pDAO.create(p1);
            pDAO.create(p2);
            pDAO.create(p3);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void popularLojas() {
        StoreDAO sDAO = new StoreDAO();
        Address add1 = new Address();
        Address add2 = new Address();
        Address add3 = new Address();
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        State state1 = new State();
        State state2 = new State();
        Store store1 = new Store();
        Store store2 = new Store();
        Store store3 = new Store();
        
        user1.setEmail("loja1@hotmail.com");
        user1.setPassword("123");
        user1.setLevel(101);
        
        user2.setEmail("loja2@hotmail.com");
        user2.setPassword("123");
        user2.setLevel(101);
        
        user3.setEmail("loja3@hotmail.com");
        user3.setPassword("123");
        user3.setLevel(101);
        
        state1.setAtivo(true);
        state1.setDtCreate(new Date());
        state1.setName("São Paulo");
        
        state2.setAtivo(true);
        state2.setDtCreate(new Date());
        state2.setName("Rio de Janeiro");
        
        city1.setAtivo(true);
        city1.setDtCreate(new Date());
        city1.setName("Mogi das Cruzes");
        city1.setState(state1);
        
        city2.setAtivo(true);
        city2.setDtCreate(new Date());
        city2.setName("Suzano");
        city2.setState(state1);
        
        city3.setAtivo(true);
        city3.setDtCreate(new Date());
        city3.setName("Rio de Janeiro");
        city3.setState(state2);
        
        add1.setAtivo(true);
        add1.setCity(city1);
        add1.setComplement("Casa");
        add1.setDtCreate(new Date());
        add1.setNeighborhood("Alto do Ipiranga");
        add1.setNumber("1480");
        add1.setStreet("Av. Japão");
        add1.setZip("08730330");
        
        add2.setAtivo(true);
        add2.setCity(city2);
        add2.setComplement("Casa");
        add2.setDtCreate(new Date());
        add2.setNeighborhood("Centro");
        add2.setNumber("321");
        add2.setStreet("Rua Central");
        add2.setZip("08645321");
        
        add3.setAtivo(true);
        add3.setCity(city3);
        add3.setComplement("Casa");
        add3.setDtCreate(new Date());
        add3.setNeighborhood("Botafogo");
        add3.setNumber("752");
        add3.setStreet("Rua Ipanema");
        add3.setZip("08956432");
        
        store1.setAddress(add1);
        store1.setName("CPGuns Alto do Ipiranga");
        store1.setLevel(101);
        store1.setUser(user1);
        
        store2.setAddress(add2);
        store2.setName("CPGuns Suzano");
        store2.setLevel(101);
        store2.setUser(user2);
        
        store3.setAddress(add3);
        store3.setName("CPGuns Botafogo");
        store3.setLevel(101);
        store3.setUser(user3);
        
        try {
            sDAO.create(store1);
            sDAO.create(store2);
            sDAO.create(store3);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fazerPedidoTeste(){
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        Product p1 = new Product();
        Product p2 = new Product();
        Carrinho carrinho = new Carrinho();
        List<Product> products = new ArrayList<>();
        Costumer costumer = new Costumer();
        Card card = new Card();
        Order o = new Order();
        Store s = new Store();
        
        
        costumer.setId(1);
        s.setId(2);
        card.setCsc("123");
        card.setAtivo(true);
        card.setDtCreate(new Date());
        card.setMonth("1");
        card.setName("GUSTAVO B");
        card.setNumber("123456789");
        card.setYear("2020");
        
        o.setCard(card);
        o.setCostumer(costumer);
        o.setStore(s);
        o.setValorTotal(4500);
        
        p1.setId(1);
        p2.setId(2);
        try {
            p1 = (Product) productDAO.read(p1).get(0);
            p1.setQtdeCarrinho(2);
            p2 = (Product) productDAO.read(p2).get(0);
            p2.setQtdeCarrinho(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        products.add(p1);
        products.add(p2);
        carrinho.setProducts(products);
        
        o.setCarrinho(carrinho);
        o.setAutorizacao("123828937128937");
        o.setStatus(Status.EM_NEGOCIACAO);
        try {
            orderDAO.create(o);
        } catch (SQLException ex) {
            Logger.getLogger(TestaOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void popularUsuario() {
        CostumerDAO costumerDAO = new CostumerDAO();
        Costumer costumer = new Costumer();
        User usuario = new User();
        Autorizacao autorizacao = new Autorizacao();
        
        autorizacao.setAutorizacao("3");
        autorizacao.setCpf("44566843807");
        
        usuario.setAtivo(true);
        usuario.setDtCreate(new Date());
        usuario.setEmail("leticia@hotmail.com");
        usuario.setLevel(100);
        usuario.setPassword("123");
        
        costumer.setAtivo(true);
        costumer.setCpf("44566843807");
        costumer.setDtBirth(new Date());
        costumer.setDtCreate(new Date());
        costumer.setGenre("Feminino");
        costumer.setName("Leticia");
        costumer.setPhoneNumber("11971636953");
        costumer.setRg("405368768");
        costumer.setUser(usuario);
        costumer.setAutorizacao(autorizacao);
        
        try {
            costumerDAO.create(costumer);
        } catch (SQLException ex) {
            Logger.getLogger(ConstruirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void popularAutorizacao(AutorizacaoDAO autorizaccaoDAO) throws SQLException{
        Autorizacao auto;
        
        auto = new Autorizacao();
        auto.setAutorizacao("1");
        auto.setTipo(TipoAutorizacao.CIVIL);
        auto.setCpf("83767312891");
        auto.setNivel(1);
        
        autorizaccaoDAO.create(auto);
        
        auto = new Autorizacao();
        auto.setAutorizacao("2");
        auto.setTipo(TipoAutorizacao.CIVIL);
        auto.setCpf("40562486801");
        auto.setNivel(2);
        
        autorizaccaoDAO.create(auto);
        
        auto = new Autorizacao();
        auto.setAutorizacao("3");
        auto.setTipo(TipoAutorizacao.CIVIL);
        auto.setCpf("44566843807");
        auto.setNivel(3);
        
        autorizaccaoDAO.create(auto);
        
        auto = new Autorizacao();
        auto.setAutorizacao("4");
        auto.setTipo(TipoAutorizacao.POLICIAL);
        auto.setCpf("19886972041");
        auto.setNivel(1);
        
        autorizaccaoDAO.create(auto);
        
        auto = new Autorizacao();
        auto.setAutorizacao("5");
        auto.setTipo(TipoAutorizacao.POLICIAL);
        auto.setCpf("48115293083");
        auto.setNivel(2);
        
        autorizaccaoDAO.create(auto);
        
        auto = new Autorizacao();
        auto.setAutorizacao("6");
        auto.setTipo(TipoAutorizacao.POLICIAL);
        auto.setCpf("32905728035");
        auto.setNivel(3);
        
        autorizaccaoDAO.create(auto);
    }
}
