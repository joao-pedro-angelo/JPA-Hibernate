package org.studies.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produtos")
// Caso o nome da tabela seja modificado, basta mudar o @ acima
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "desc")
    private String descricao;
    private BigDecimal preco;
    @ManyToOne
    private Category category;

    public Product() {}

    public Product(String nome, String descricao, BigDecimal preco, Category category) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.category = category;
    }

    public Category getCategoria(){
        return this.category;
    }
}
