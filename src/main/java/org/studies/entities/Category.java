package org.studies.entities;

import javax.persistence.*;

@Entity
@Table(name = "Categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Category(){}

    public Category(String nome){
        this.nome = nome;
    }
}
