  package com.team.ecommerce.entity;

  import com.fasterxml.jackson.annotation.JsonIgnore;
  import lombok.Getter;
  import lombok.Setter;

  import javax.persistence.*;
  import java.util.List;

@Entity(name = "product_category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Field> fields;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
