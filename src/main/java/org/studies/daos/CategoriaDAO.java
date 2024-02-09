package org.studies.daos;

import org.studies.entities.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrarCategoria(Categoria categoria){
        this.em.persist(categoria);
    }
}
