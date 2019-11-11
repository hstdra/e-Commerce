package com.team.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "User_Constraint", columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 191)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private String fullname;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL DEFAULT 'ROLE_CUSTOMER'")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}