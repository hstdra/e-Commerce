package com.team.ecommerce.repository;

import com.team.ecommerce.entity.FieldDetail;
import com.team.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldDetailRepository extends JpaRepository<FieldDetail, Integer> {
    List<FieldDetail> getAllByProduct(Product product);
}
