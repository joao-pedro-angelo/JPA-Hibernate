/*
 * Serviço para operações relacionadas à entidade Product.
 * Fornece métodos para criar, remover, atualizar e ler produtos do banco de dados.
 * Gerencia transações com o banco de dados através do EntityManager.
 */
package org.studies.services;

import org.studies.daos.ProductDAO;
import org.studies.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceProduct {

    private ProductDAO productDAO;
    private EntityManager entityManager;

    /*
     * Construtor da classe ServiceProduct.
     * @param productDAO O DAO de Product utilizado para interações com o banco de dados.
     * @param entityManager O EntityManager utilizado para gerenciar transações com o banco de dados.
     */
    public ServiceProduct(ProductDAO productDAO, EntityManager entityManager) {
        this.productDAO = productDAO;
        this.entityManager = entityManager;
    }

    /*
     * Método para criar um novo produto no banco de dados.
     * @param product O produto a ser criado.
     */
    public void createProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.createProduct(product);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para remover um produto do banco de dados.
     * @param product O produto a ser removido.
     */
    public void removeProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.removeProduct(product);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para atualizar um produto existente no banco de dados.
     * @param product O produto a ser atualizado.
     */
    public void updateProduct(Product product){
        this.entityManager.getTransaction().begin();
        this.productDAO.updateProduct(product);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para ler um produto do banco de dados com base em sua chave primária.
     * @param key A chave primária do produto a ser lido.
     * @return O produto encontrado ou null se não existir.
     */
    public Product readProduct(String key){
        this.entityManager.getTransaction().begin();
        Product product = this.productDAO.readProduct(key);
        this.entityManager.getTransaction().commit();
        return product;
    }

    /*
     * Método para ler todos os produtos do banco de dados.
     * @return Uma string contendo a representação de todos os produtos encontrados.
     */
    public String readAllProducts(){
        StringBuilder result = new StringBuilder();
        this.entityManager.getTransaction().begin();
        List<Product> products = this.productDAO.readProducts();
        this.entityManager.getTransaction().commit();

        for (Product product : products){
            result.append(product.toString());
        }
        return result.toString();
    }

    /*
     * Método para fechar o EntityManager.
     */
    public void closeEntityManager(){
        this.entityManager.close();
    }
}
