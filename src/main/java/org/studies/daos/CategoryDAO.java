package org.studies.daos;

import org.studies.entities.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {

    private EntityManager entityManager;

    public CategoryDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void createCategory(Category category){
        this.entityManager.persist(category);
    }

    public void updateCategory(Category category){
        this.entityManager.merge(category);
        this.entityManager.flush();
    }

    public void removeCategory(Category category){
        category = this.entityManager.merge(category);
        this.entityManager.remove(category);
    }
}
