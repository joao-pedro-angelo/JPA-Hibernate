package org.studies.services;

import org.studies.daos.ProductDAO;
import org.studies.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

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

    public String readAllProducts(){
        StringBuilder result = new StringBuilder();
        this.entityManager.getTransaction().begin();
        List<Product> products = this.productDAO.readProducts();
        this.entityManager.getTransaction().commit();
        this.entityManager.close();

        for (Product product : products){
            result.append(product.toString());
        }
        return result.toString();
    }
}
