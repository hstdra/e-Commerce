package com.team.ecommerce.repository;

import com.team.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("FROM Product")
    public List<Product> getAll(Pageable pageable);
}
