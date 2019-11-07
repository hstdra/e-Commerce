package com.team.ecommerce.repository;

import com.team.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("FROM Order WHERE user_id = ?1 AND status = 0")
    Order getCustomerShopCart(int userId);

    @Query("FROM Order WHERE user_id = ?1 AND status <> 0 ORDER BY ID DESC")
    List<Order> findAllByUser_IdOrderByIdDesc(int userId);

    @Query("FROM Order WHERE status <> 0 ORDER BY ID DESC")
    List<Order> findAllExceptCart();
}