package org.studies;

import org.studies.JPAUtil.CreateEntityManeger;
import org.studies.daos.ProdutoDAO;
import org.studies.entities.Produto;
import org.studies.entities.enums.Categoria;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // Criação do objeto que será persistido - Simula um usuário
        Produto celularXiamo = new Produto("Celular XIAOMI", "Um celular",
                new BigDecimal("800"), Categoria.CELULAR);

        // Criação do Entity Manager
        EntityManager em = CreateEntityManeger.createEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);

        // Início e conclusão da transação
        em.getTransaction().begin();
        dao.cadastrarProduto(celularXiamo);
        em.getTransaction().commit();
        em.close();
    }

}