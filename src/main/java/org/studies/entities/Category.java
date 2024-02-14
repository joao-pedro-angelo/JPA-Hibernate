package org.studies.entities;

import javax.persistence.*;

/**
 * Classe para representar uma Categoria
 * Entidade Categoria - Representada por uma tabela na base de dados
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
@Entity
@Table(name = "CategoryTable")
public class Category {

    /**
     * O identificador de uma categoria é o nome
     */
    @Id
    private String name;

    /**
     * Construtor padrão da entidade
     */
    public Category(){}

    /**
     * Construtor que recebe o nome
     * @param name String nome da entidade
     */
    public Category(String name){
        this.name = name;
    }

    /**
     * Método setter para o nome
     * @param name String nome da categoria
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Representação textual da entidade
     * @return Representação textual da entidade
     */
    public String toString(){
        return "Categoria: " + this.name + "\n";
    }
}
