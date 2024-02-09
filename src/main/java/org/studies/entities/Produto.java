package org.studies.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produtos")
// Caso o nome da tabela seja modificado, basta mudar o @ acima
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "desc")
    private String descricao;
    private BigDecimal preco;
    @ManyToOne
    private Categoria categoria;

    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }
}
