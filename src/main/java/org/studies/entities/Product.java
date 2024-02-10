package org.studies.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ProductTable")
public class Product {

    @Id
    @NotNull
    private String name;
    @Column(name = "quant")
    @NotNull
    private Integer quantity;
    @Column(name = "desc")
    private String description;
    @NotNull
    private BigDecimal value;
    @ManyToOne
    @NotNull
    private Category category;

    public Product() {}

    public Product(String name, Integer quantity, String description, BigDecimal value, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setValue(BigDecimal value){
        this.value = value;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", quantity=" + this.quantity +
                ", description='" + this.description + '\'' +
                ", value=" + this.value +
                ", category=" + this.category +
                '}';
    }
}
