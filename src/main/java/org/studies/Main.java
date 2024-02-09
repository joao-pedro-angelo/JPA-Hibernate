package org.studies;

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
        // A String passa é o nome da base de dados, está presente no arquivo persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("StudiesJPA");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(celularXiamo);
        em.getTransaction().commit();
        em.close();
        // Saída: Hibernate: insert into Produtos (id, desc, nome, preco) values (null, ?, ?, ?)
    }

}