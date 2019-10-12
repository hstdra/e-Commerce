package com.team.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false)
    private String fullname;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL DEFAULT 'ROLE_USER'")
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders;
    
    private Boolean emailExist = false;
}