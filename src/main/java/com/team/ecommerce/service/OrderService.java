package com.team.ecommerce.service;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.repository.OrderRepository;
import com.team.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public Order getShopCart(int userId) {
        return orderRepository.getCustomerShopCart(userId);
    }

    public Order createShopCart(int userId) {
        Order cart = new Order();
        cart.setStatus(0);
        cart.setUser(userRepository.getOne(userId));
        cart.setOrderDetails(new ArrayList<>());
        orderRepository.save(cart);
        return cart;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(int orderId) {
        return orderRepository.getOne(orderId);
    }

    public List<Order> getOrderHistoryByUser(int userId) {
        return orderRepository.findAllByUser_IdOrderByIdDesc(userId);
    }

    public void cancelOrder(int orderId) {
        Order order = getOrder(orderId);
        if (order.getStatus() < 10)
            order.setStatus(5);
        else order.setStatus(15);
        orderRepository.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAllExceptCart();
    }

}
