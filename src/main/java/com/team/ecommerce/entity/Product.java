package com.team.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;
    private String name;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;
    private Long price;
    private Long discount;
    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String description;
    @OneToMany(mappedBy = "id.field")
    private List<FieldDetail> fields;
}
