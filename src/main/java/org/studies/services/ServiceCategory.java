package org.studies.services;

import org.studies.daos.CategoryDAO;
import org.studies.entities.Category;

import javax.persistence.EntityManager;

public class ServiceCategory {

    private CategoryDAO categoryDAO;
    private EntityManager entityManager;

    public ServiceCategory(CategoryDAO categoryDAO, EntityManager entityManager){
        this.categoryDAO = categoryDAO;
        this.entityManager = entityManager;
    }

    public void createCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.createCategory(category);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void removeCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.removeCategory(category);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void updateCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.updateCategory(category);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public Category readCategory(String key){
        this.entityManager.getTransaction().begin();
        Category category = this.categoryDAO.readCategory(key);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return  category;
    }
}
