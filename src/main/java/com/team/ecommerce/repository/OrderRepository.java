package com.team.ecommerce.repository;

import com.team.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("FROM Order WHERE user_id = ?1 AND status = 0")
    Order getCustomerShopCart(int user_id);
}