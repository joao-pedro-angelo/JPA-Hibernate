/*
 * Serviço para operações relacionadas à entidade Category.
 * Fornece métodos para criar, remover, atualizar e ler categorias do banco de dados.
 * Gerencia transações com o banco de dados através do EntityManager.
 */
package org.studies.services;

import org.studies.daos.CategoryDAO;
import org.studies.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceCategory {

    private CategoryDAO categoryDAO;
    private EntityManager entityManager;

    /*
     * Construtor da classe ServiceCategory.
     * @param categoryDAO O DAO de Category utilizado para interações com o banco de dados.
     * @param entityManager O EntityManager utilizado para gerenciar transações com o banco de dados.
     */
    public ServiceCategory(CategoryDAO categoryDAO, EntityManager entityManager){
        this.categoryDAO = categoryDAO;
        this.entityManager = entityManager;
    }

    /*
     * Método para criar uma nova categoria no banco de dados.
     * @param category A categoria a ser criada.
     */
    public void createCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.createCategory(category);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para remover uma categoria do banco de dados.
     * @param category A categoria a ser removida.
     */
    public void removeCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.removeCategory(category);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para atualizar uma categoria existente no banco de dados.
     * @param category A categoria a ser atualizada.
     */
    public void updateCategory(Category category){
        this.entityManager.getTransaction().begin();
        this.categoryDAO.updateCategory(category);
        this.entityManager.getTransaction().commit();
    }

    /*
     * Método para ler uma categoria do banco de dados com base em sua chave primária.
     * @param key A chave primária da categoria a ser lida.
     * @return A categoria encontrada ou null se não existir.
     */
    public Category readCategory(String key){
        this.entityManager.getTransaction().begin();
        Category category = this.categoryDAO.readCategory(key);
        this.entityManager.getTransaction().commit();
        return category;
    }

    /*
     * Método para ler todas as categorias do banco de dados.
     * @return Uma string contendo a representação de todas as categorias encontradas.
     */
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

    /*
     * Método para fechar o EntityManager.
     */
    public void closeEntityManager(){
        this.entityManager.close();
    }
}
