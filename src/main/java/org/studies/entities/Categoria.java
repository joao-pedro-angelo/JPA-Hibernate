package org.studies.entities;

import javax.persistence.*;

@Entity
@Table(name = "Categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
    }
}
