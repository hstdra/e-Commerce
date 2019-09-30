package com.team.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 12)
    private String phone;
    @Column(nullable = false)
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}