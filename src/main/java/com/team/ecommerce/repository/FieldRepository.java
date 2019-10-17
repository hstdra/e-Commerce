package com.team.ecommerce.repository;

import com.team.ecommerce.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    Field getFirstByCategory_CategoryAndField(String category, String field);
}
