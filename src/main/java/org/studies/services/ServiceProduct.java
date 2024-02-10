package org.studies.services;

import org.studies.daos.ProductDAO;
import org.studies.entities.Product;

import javax.persistence.EntityManager;

public class ServiceProduct {

    private ProductDAO productDAO;
    private EntityManager entityManager;

    public ServiceProduct(ProductDAO productDAO, EntityManager entityManager) {
        this.productDAO = productDAO;
        this.entityManager = entityManager;
    }

    public void createProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.createProduct(product);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void removeProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.removeProduct(product);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void updateProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.updateProduct(product);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public Product readProduct(String key){
        this.entityManager.getTransaction().begin();
        Product product = this.productDAO.readProduct(key);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
        return  product;
    }
}
