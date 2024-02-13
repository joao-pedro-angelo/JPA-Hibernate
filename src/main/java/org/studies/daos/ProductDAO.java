package org.studies.daos;

import org.studies.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void createProduct(Product product){
        this.entityManager.persist(product);
    }

    public void updateProduct(Product product){
        this.entityManager.merge(product);
        this.entityManager.flush();
    }

    public void removeProduct(Product product){
        product = this.entityManager.merge(product);
        this.entityManager.remove(product);
    }

    public Product readProduct(String key){
        return this.entityManager.find(Product.class, key);
    }

    public List<Product> readProducts(){
        String jpql = "SELECT p FROM Product p";
        return this.entityManager.createQuery(jpql, Product.class).getResultList();
    }
}
