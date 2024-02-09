package org.studies.daos;

import org.studies.entities.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrarCategoria(Category category){
        this.em.persist(category);
    }

    public void atualizaCategoria(Category category){
        this.em.merge(category);
    }
}
