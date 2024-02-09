package org.studies;

import org.studies.JPAUtil.CreateEntityManeger;
import org.studies.daos.CategoriaDAO;
import org.studies.daos.ProdutoDAO;
import org.studies.entities.Categoria;
import org.studies.entities.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Criação do objeto que será persistido - Simula um usuário
        Produto celularXiamo = new Produto("Celular XIAOMI", "Um celular",
                new BigDecimal("800"), new Categoria("Informática"));

        // Criação do Entity Manager
        EntityManager em = CreateEntityManeger.createEntityManager();
        ProdutoDAO productDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        // Início e conclusão da transação
        em.getTransaction().begin();
        categoriaDao.cadastrarCategoria(celularXiamo.getCategoria());
        productDao.cadastrarProduto(celularXiamo);
        em.getTransaction().commit();
        em.close();
    }

}