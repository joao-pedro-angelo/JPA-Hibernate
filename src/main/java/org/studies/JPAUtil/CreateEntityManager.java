package org.studies.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe Utilitária para criar uma instância de EntityManager
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class CreateEntityManager {

    // FACTORY
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("StudiesJPA");

    /**
     * Cria a entityManager
     * @return instância de EntityManager
     */
    public static EntityManager createEntityManager(){
        return FACTORY.createEntityManager();
    }

}
