package com.team.ecommerce.service;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.OrderDetail;
import com.team.ecommerce.repository.OrderDetailRepository;
import com.team.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderDetail findProductInCart(Integer product_id, Order cart) {
        try {
            return cart.getOrderDetails().stream().filter(od -> od.getProduct().getId().equals(product_id)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public void addProductToCart(Integer product_id, Order cart) {
        OrderDetail orderDetail = findProductInCart(product_id, cart);
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setProduct(productRepository.getOne(product_id));
            orderDetail.setOrder(cart);
            orderDetail.setQuantity(1);
            orderDetail.setPrice(orderDetail.getProduct().getFinalPrice());
        } else {
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
        }
        orderDetailRepository.save(orderDetail);
    }

    public void minusProductFromCart(Integer product_id, Order cart) {
        OrderDetail orderDetail = findProductInCart(product_id, cart);
        if (orderDetail != null) {
            orderDetail.setQuantity(orderDetail.getQuantity() - 1);
            if (orderDetail.getQuantity() <= 0)
                deleteProductFromCart(product_id, cart);
            else
                orderDetailRepository.save(orderDetail);
        }
    }

    public void deleteProductFromCart(Integer product_id, Order cart) {
        try {
            orderDetailRepository.delete(findProductInCart(product_id, cart));
        } catch (Exception ignored) {
        }
    }
}

