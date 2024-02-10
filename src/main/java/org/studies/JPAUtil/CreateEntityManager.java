package org.studies.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEntityManager {

    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("StudiesJPA");

    public static EntityManager createEntityManager(){
        return FACTORY.createEntityManager();
    }

}
