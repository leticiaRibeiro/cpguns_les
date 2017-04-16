/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpguns.core.facade;

/**
 *
 * @author Leticia
 */
import com.cpguns.core.app.Result;
import com.cpguns.core.dao.IDAO;
import com.cpguns.core.dao.impl.AddressDAO;
import com.cpguns.core.dao.impl.CostumerDAO;
import com.cpguns.core.dao.impl.ManufacturerDAO;
import com.cpguns.core.dao.impl.ProductDAO;
import com.cpguns.core.model.Address;
import com.cpguns.core.model.Costumer;
import com.cpguns.core.model.DomainEntity;
import com.cpguns.core.model.Manufacturer;
import com.cpguns.core.model.Product;
import com.cpguns.core.strategy.IStrategy;
import com.cpguns.core.strategy.ValidarDadosObrigatorios;
import com.cpguns.core.strategy.ValidarIdade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {

    /**
     * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância
     * do DAO para uma dada entidade;
     */
    private Map<String, IDAO> daos;

    /**
     * Mapa para conter as regras de negócio de todas operações por entidade; O
     * valor é um mapa que de regras de negócio indexado pela operação
     */
    private Map<String, Map<String, List<IStrategy>>> rns;

    private Result result;

    public Facade() {
        /* Instanciando o Map de DAOS */
        daos = new HashMap<String, IDAO>();
        /* Instanciando o Map de Regras de Negócio */
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        /* Criando instâncias dos DAOs a serem utilizados*/
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        CostumerDAO costDAO = new CostumerDAO();
        ProductDAO prodDAO = new ProductDAO();
        AddressDAO addrDAO = new AddressDAO();

        /* Adicionando cada dao no MAP indexando pelo nome da classe */
        daos.put(Manufacturer.class.getName(), manuDAO);
        daos.put(Costumer.class.getName(), costDAO);
        daos.put(Product.class.getName(), prodDAO);
        daos.put(Address.class.getName(), addrDAO);


        /* Criando instâncias de regras de negócio a serem utilizados*/
        ValidarIdade validarIdade = new ValidarIdade();
        ValidarDadosObrigatorios validarDadosObg = new ValidarDadosObrigatorios();


        /* Criando uma lista para conter as regras de negócio do cliente
         * quando a operação for salvar
         */
        List<IStrategy> rnsCreateCostumer = new ArrayList<IStrategy>();
        List<IStrategy> rnsReadCostumer = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar do fornecedor*/
        rnsCreateCostumer.add(validarDadosObg);
		////////////////////////////////////////rnsCreateCostumer.add(vCnpj);

        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica
         * por operação  do fornecedor
         */
        Map<String, List<IStrategy>> rnsCostumer = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista criada na linha 70)
         */
        rnsCostumer.put("SALVAR", rnsCreateCostumer);

        /* Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações no mapa geral indexado
         * pelo nome da entidade
         */
        rns.put(Costumer.class.getName(), rnsCostumer);

    }

    @Override
    public Result create(DomainEntity entity) {
        result = new Result();
        String nmClasse = entity.getClass().getName();

        String msg = executeRules(entity, "SALVAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.create(entity);
                List<DomainEntity> entities = new ArrayList<DomainEntity>();
                entities.add(entity);
                result.setEntities(entities);
            } catch (SQLException e) {
                e.printStackTrace();
                result.setMsg("Não foi possível realizar o registro!");
            }
        } else {
            result.setMsg(msg);
        }

        return result;
    }

    @Override
    public Result update(DomainEntity entity) {
        result = new Result();
        String nmClasse = entity.getClass().getName();

        String msg = executeRules(entity, "ALTERAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.update(entity);
                List<DomainEntity> entities = new ArrayList<DomainEntity>();
                entities.add(entity);
                result.setEntities(entities);
            } catch (SQLException e) {
                e.printStackTrace();
                result.setMsg("Não foi possível realizar o registro!");

            }
        } else {
            result.setMsg(msg);
        }
        return result;
    }

    @Override
    public Result delete(DomainEntity entity) {
        result = new Result();
        String nmClasse = entity.getClass().getName();

        String msg = executeRules(entity, "EXCLUIR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.delete(entity);
                List<DomainEntity> entities = new ArrayList<DomainEntity>();
                entities.add(entity);
                result.setEntities(entities);
            } catch (SQLException e) {
                e.printStackTrace();
                result.setMsg("Não foi possível realizar o registro!");
            }
        } else {
            result.setMsg(msg);
        }
        return result;
    }

    @Override
    public Result read(DomainEntity entity) {
        result = new Result();
        String nmClasse = entity.getClass().getName();

        String msg = executeRules(entity, "CONSULTAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                result.setEntities(dao.read(entity));
            } catch (SQLException e) {
                e.printStackTrace();
                result.setMsg("Não foi possível realizar a consulta!");
            }
        } else {
            result.setMsg(msg);
        }
        return result;
    }

    private String executeRules(DomainEntity entity, String operation) {
        String nmClasse = entity.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operation);

            if (regras != null) {
                for (IStrategy s : regras) {
                    String m = s.process(entity);
                    if (m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }
        }
        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
