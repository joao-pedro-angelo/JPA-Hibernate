package org.studies.daos;

import org.studies.entities.Product;

import javax.persistence.EntityManager;

public class ProductDAO {

    private EntityManager em;

    // Inversão de controle e injeção de dependências;
    public ProductDAO(EntityManager em){
        this.em = em;
    }

    // Cadastrar Produto - Repare como é um método bem mais enxuto do que o da JDBC pura
    public void cadastrarProduct(Product product){
        this.em.persist(product);
    }

    public void atualizarProduct(Product product){
        this.em.merge(product);
    }
}
