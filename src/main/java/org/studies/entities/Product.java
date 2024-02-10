package org.studies.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ProductTable")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "desc")
    private String description;
    private BigDecimal value;
    @ManyToOne
    private Category category;

    public Product() {}

    public Product(String name, String description, BigDecimal value, Category category) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.category = category;
    }
}
