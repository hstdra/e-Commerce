package com.team.ecommerce;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.OrderDetailService;
import com.team.ecommerce.service.OrderService;
import com.team.ecommerce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Test
    public void contextLoads() {

    }

    @Test
    public void testEmail() {
        User user = userService.getByEmail("customer@gmail.com");
        System.out.println();
    }

    @Test
    public void testShopCart() {
        Order cart = orderService.getShopCart(2);
        orderDetailService.addProductToCart(1, cart);
        orderDetailService.addProductToCart(2, cart);
        cart = orderService.getShopCart(2);

        System.out.println();
    }
}
