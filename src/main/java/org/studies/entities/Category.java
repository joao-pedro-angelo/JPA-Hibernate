package org.studies.entities;

import javax.persistence.*;

@Entity
@Table(name = "CategoryTable")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Category(){}

    public Category(String name){
        this.name = name;
    }
}
