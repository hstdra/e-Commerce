package com.team.ecommerce.repository;

import com.team.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getFirstByCategory(String category);
}
