package org.studies;

import org.studies.daos.ProdutoDAO;
import org.studies.entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Criação do objeto que será persistido
        Produto celularXiamo = new Produto();
        celularXiamo.setNome("Celular XIAMO");
        celularXiamo.setDescricao("Um celular");
        celularXiamo.setPreco(new BigDecimal("800"));

        // Criação da interface que irá interagir com a tabela
        // A String passada é o nome da base de dados, está presente no arquivo persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("StudiesJPA");
        EntityManager em = factory.createEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);

        em.getTransaction().begin();
        dao.cadastrarProduto(celularXiamo);
        em.getTransaction().commit();
        em.close();
    }

}