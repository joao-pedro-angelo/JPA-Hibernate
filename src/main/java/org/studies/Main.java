package org.studies;

import org.studies.JPAUtil.CreateEntityManeger;
import org.studies.daos.CategoryDAO;
import org.studies.daos.ProductDAO;
import org.studies.entities.Category;
import org.studies.entities.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Criação do objeto que será persistido - Simula um usuário
        Product celularXiamo = new Product("Celular XIAOMI", "Um celular",
                new BigDecimal("800"), new Category("Informática"));

        // Criação do Entity Manager
        EntityManager em = CreateEntityManeger.createEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        CategoryDAO categoryDao = new CategoryDAO(em);

        // Início e conclusão da transação
        em.getTransaction().begin();
        categoryDao.cadastrarCategoria(celularXiamo.getCategoria());
        productDao.cadastrarProduto(celularXiamo);
        em.getTransaction().commit();
        em.close();
    }

}