package org.studies.daos;

import org.studies.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Classe responsável por interagir com o banco de dados para operações relacionadas à entidade Category.
 * Utiliza o padrão DAO (Data Access Object) para encapsular o acesso aos dados.
 * @author carneiroangelojoaopedro@gmail.com
 */
public class CategoryDAO {

    private EntityManager entityManager;

    /**
     * Construtor da classe que recebe um EntityManager como parâmetro.
     * @param entityManager O EntityManager a ser utilizado para interação com o banco de dados.
     */
    public CategoryDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * Método para criar uma nova categoria no banco de dados.
     * @param category A categoria a ser persistida no banco de dados.
     */
    public void createCategory(Category category){
        this.entityManager.persist(category);
    }

    /**
     * Método para atualizar uma categoria existente no banco de dados.
     * @param category A categoria a ser atualizada.
     */
    public void updateCategory(Category category){
        this.entityManager.merge(category);
        this.entityManager.flush();
    }

    /**
     * Método para remover uma categoria do banco de dados.
     * @param category A categoria a ser removida.
     */
    public void removeCategory(Category category){
        category = this.entityManager.merge(category);
        this.entityManager.remove(category);
    }

    /**
     * Método para buscar uma categoria no banco de dados com base em sua chave primária.
     * @param key A chave primária da categoria a ser buscada.
     * @return A categoria encontrada ou null se não existir.
     */
    public Category readCategory(String key){
        return this.entityManager.find(Category.class, key);
    }

    /**
     * Método para recuperar todas as categorias do banco de dados.
     * @return Uma lista contendo todas as categorias armazenadas no banco de dados.
     */
    public List<Category> readCategories(){
        String jpql = "SELECT c FROM Category c";
        return this.entityManager.createQuery(jpql, Category.class).getResultList();
    }
}
