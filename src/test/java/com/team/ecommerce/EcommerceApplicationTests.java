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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {
    private final static Random random = new Random();
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
            add("1600MHz");
            add("2133MHz");
            add("2400MHz");
            add("2666MHz");
            add("3000MHz");
            add("3200MHz");
            add("3600MHz");
        }};
        ArrayList<String> hang = new ArrayList<String>() {{
            add("TEAM");
            add("G.SKILL");
            add("CORSAIR");
            add("KINGMAX");
            add("KINGSTON");
            add("GIGABYTE");
            add("ADATA");
        }};
        ArrayList<String> dungluong = new ArrayList<String>() {{
            add("2GB");
            add("4GB");
            add("8GB");
            add("16GB");
            add("32GB");
        }};
        ArrayList<String> ecc = new ArrayList<String>() {{
            add("Có");
            add("Không");
        }};
        ArrayList<String> led = new ArrayList<String>() {{
            add("RGB");
            add("Không");
            add("Đơn sắc");
        }};
        ArrayList<String> gen = new ArrayList<String>() {{
            add("DDR3");
            add("DDR3L");
            add("DDR4");
        }};
        ArrayList<String> loai = new ArrayList<String>() {{
            add("Laptop");
            add("Desktop");
        }};

        for (int i = 0; i < 2347; i++) {
            String category = "Ram";
            long price = random.nextInt(6500) * 1000 + 600000;
            long discount = Math.round(price * (100 - random.nextInt(30)) / 100000) * 1000;
            int quantity = 10;
            String b = bus.get(random.nextInt(bus.size()));
            String h = hang.get(random.nextInt(hang.size()));
            String dl = dungluong.get(random.nextInt(dungluong.size()));
            String e = ecc.get(random.nextInt(ecc.size()));
            String ld = led.get(random.nextInt(led.size()));
            String g = gen.get(random.nextInt(gen.size()));
            String l = loai.get(random.nextInt(loai.size()));
            String name = String.format("%s %s %s %s %s %s", category, l, h, dl, g, b);

            Map<String, String> fieldDetails = new HashMap<String, String>() {
                {
                    put("Bus", b);
                    put("Hãng", h);
                    put("Dung lượng", dl);
                    put("ECC", e);
                    put("Led", ld);
                    put("Loại", l);
                    put("Thế hệ ram", g);
                }
            };
            productService.createProduct(name, category, price, discount, quantity, fieldDetails);
        }
    }
}
