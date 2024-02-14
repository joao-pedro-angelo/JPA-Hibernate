package org.studies.services;

import org.studies.daos.CategoryDAO;
import org.studies.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;

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
    }

    public void removeCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.removeCategory(category);
        this.entityManager.getTransaction().commit();
    }

    public void updateCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.updateCategory(category);
        this.entityManager.getTransaction().commit();
    }

    public Category readCategory(String key){
        this.entityManager.getTransaction().begin();
        Category category = this.categoryDAO.readCategory(key);
        this.entityManager.getTransaction().commit();
        return category;
    }

    public String readAllCategories(){
        StringBuilder result = new StringBuilder();
        this.entityManager.getTransaction().begin();
        List<Category> categories = this.categoryDAO.readCategories();
        this.entityManager.getTransaction().commit();

        for (Category category : categories){
            result.append(category.toString());
        }
        return result.toString();
    }

    public void closeEntityManager(){
        this.entityManager.close();
    }
}
