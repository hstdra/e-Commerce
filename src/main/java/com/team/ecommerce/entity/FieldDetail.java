package com.team.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "field_detail")
@Getter
@Setter
public class FieldDetail {
    @EmbeddedId
    private FieldDetailPK id;
    private String detail;
}


@Embeddable
class FieldDetailPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "product_field_id")
    private Field field;
}