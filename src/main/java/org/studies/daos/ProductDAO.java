/*
 * Classe responsável por interagir com o banco de dados para operações relacionadas à entidade Product.
 * Utiliza o padrão DAO (Data Access Object) para encapsular o acesso aos dados.
 * @author carneiroangelojoaopedro@gmail.com
 */
package org.studies.daos;

import org.studies.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager;

    /*
     * Construtor da classe que recebe um EntityManager como parâmetro.
     * @param entityManager O EntityManager a ser utilizado para interação com o banco de dados.
     */
    public ProductDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /*
     * Método para criar um novo produto no banco de dados.
     * @param product O produto a ser persistido no banco de dados.
     */
    public void createProduct(Product product){
        this.entityManager.persist(product);
    }

    /*
     * Método para atualizar um produto existente no banco de dados.
     * @param product O produto a ser atualizado.
     */
    public void updateProduct(Product product){
        this.entityManager.merge(product);
        this.entityManager.flush();
    }

    /*
     * Método para remover um produto do banco de dados.
     * @param product O produto a ser removido.
     */
    public void removeProduct(Product product){
        product = this.entityManager.merge(product);
        this.entityManager.remove(product);
    }

    /*
     * Método para buscar um produto no banco de dados com base em sua chave primária.
     * @param key A chave primária do produto a ser buscado.
     * @return O produto encontrado ou null se não existir.
     */
    public Product readProduct(String key){
        return this.entityManager.find(Product.class, key);
    }

    /*
     * Método para recuperar todos os produtos do banco de dados.
     * @return Uma lista contendo todos os produtos armazenados no banco de dados.
     */
    public List<Product> readProducts(){
        String jpql = "SELECT p FROM Product p";
        return this.entityManager.createQuery(jpql, Product.class).getResultList();
    }
}
