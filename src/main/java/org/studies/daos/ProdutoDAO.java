package org.studies.daos;

import org.studies.entities.Produto;

import javax.persistence.EntityManager;

public class ProdutoDAO {

    private EntityManager em;

    // Inversão de controle e injeção de dependências;
    public ProdutoDAO(EntityManager em){
        this.em = em;
    }

    // Cadastrar Produto - Repare como é um método bem mais enxuto do que o da JDBC pura
    public void cadastrarProduto(Produto produto){
        this.em.persist(produto);
    }


}
