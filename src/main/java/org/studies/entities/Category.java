package org.studies.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "CategoryTable")
public class Category {

    @Id
    @NotNull
    private String name;

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString(){
        return "Categoria: " + this.name + "\n";
    }
}
