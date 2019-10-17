package com.team.ecommerce;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProductService productService;
    @Autowired
    FieldService fieldService;

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
        Order cart = orderService.createShopCart(2);
//        Order cart = orderService.getShopCart(2);
//        orderDetailService.addProductToCart(1, cart);
//        orderDetailService.addProductToCart(2, cart);
//        cart = orderService.getShopCart(2);

        System.out.println();
    }

    @Test
    public void testCreateProduct() {
        ArrayList<String> bus = new ArrayList<String>() {{
            add("2133");
            add("2400");
            add("2800");
        }};
        ArrayList<String> hang = new ArrayList<String>() {{
            add("SAM SUNG");
            add("G SKILL");
            add("KINGSTON");
            add("KINGMAX");
        }};
        for (int i = 0; i < 2000; i++) {
            Collections.shuffle(bus);
            Collections.shuffle(hang);
            String name = "Tét hàng thôi";
            String category = "Ram";
            long price = 20000;
            long discount = 18000;
            int quantity = 5;
            Map<String, String> fieldDetails = new HashMap<String, String>() {
                {
                    put("Bus", bus.get(0));
                    put("Hang", hang.get(0));
                    put("Dung luong", "4GB");
                }
            };
            productService.createProduct(name, category, price, discount, quantity, fieldDetails);
        }
    }
}
