package com.team.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailPK id;
    @Column(columnDefinition = "INT(10) DEFAULT 1")
    private Integer quantity;
}

@Embeddable
class OrderDetailPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}