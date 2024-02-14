package org.studies.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Representação de uma entidade na base de dados como uma tabela
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
@Entity
@Table(name = "ProductTable")
public class Product {

    /**
     * A chave da entidade produto é o nome do produto
     */
    @Id
    private String name;

    /**
     * Coluna "quant"
     * Quantidade do produto
     */
    @Column(name = "quant")
    private Integer quantity;

    /**
     * Coluna "desc"
     * Descrição do produto
     */
    @Column(name = "desc")
    private String description;

    /**
     * Coluna "value"
     * Valor do produto
     */
    private BigDecimal value;

    /**
     * Coluna Category
     * Categoria do produto
     *
     * Relacionamento ManyToOne
     * Muitos produtos para uma categoria
     */
    @ManyToOne
    private Category category;

    /**
     * Construtor padrão - default
     */
    public Product() {}

    /**
     * Construtor all-args
     */
    public Product(String name, Integer quantity, String description, BigDecimal value, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    // Setters e getters abaixo
    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setValue(BigDecimal value){
        this.value = value;
    }

    /**
     * Representação textual da categoria
     * @return representação textual
     */
    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", quantity=" + this.quantity +
                ", description='" + this.description + '\'' +
                ", value=" + this.value +
                ", category=" + this.category +
                '}' + "\n";
    }
}
