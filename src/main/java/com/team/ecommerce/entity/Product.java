package com.team.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Indexed
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @Field(termVector = TermVector.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    private String image;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;

    private Long price;

    private Long discount;

    @Field(termVector = TermVector.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "field")
    private List<FieldDetail> fields;
}
