package com.team.ecommerce;

import com.team.ecommerce.entity.Order;
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
    @Test
    public void contextLoads() {

    }

    @Test
    public void testEmail() {
        assert userService.getByEmail("customer@gmail.com").getEmail().equals("customer@gmail.com");
    }

    @Test
    public void testShopCart() {
        Order cart = orderService.createShopCart(2);
        assert cart.getId().equals(orderService.getShopCart(2).getId());
    }
}
