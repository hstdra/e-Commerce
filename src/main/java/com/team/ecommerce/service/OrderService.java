package com.team.ecommerce.service;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.repository.OrderRepository;
import com.team.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public Order getShopCart(int user_id) {
        return orderRepository.getCustomerShopCart(user_id);
    }

    public Order createShopCart(int user_id) {
        Order cart = new Order();
        cart.setStatus(0);
        cart.setUser(userRepository.getOne(user_id));
        cart.setOrderDetails(new ArrayList<>());
        return cart;
    }
}
