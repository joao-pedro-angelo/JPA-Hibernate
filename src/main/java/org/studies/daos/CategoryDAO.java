package org.studies.daos;

import org.studies.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Category readCategory(String key){
        return this.entityManager.find(Category.class, key);
    }

    public List<Category> readCategories(){
        String jpql = "SELECT c FROM Category c";
        return this.entityManager.createQuery(jpql, Category.class).getResultList();
    }
}
