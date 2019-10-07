package com.team.ecommerce.service;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;

    public Order getShopCart(int user_id) {
        return orderRepository.getCustomerShopCart(user_id);
    }

    public Order createShopCart(int user_id) {
        Order cart = new Order();
        cart.setStatus(0);
        cart.setUser(userService.get(user_id));
        orderRepository.save(cart);
        return cart;
    }
}
