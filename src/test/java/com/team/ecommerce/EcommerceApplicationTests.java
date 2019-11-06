package com.team.ecommerce;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testEmail() {
        Product product = productService.get(5540);
        System.out.println(product.getFieldDetailsFacet());
        System.out.println(product.getName());
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
        ArrayList<String> image = new ArrayList<String>() {{
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/ram/18110199/45c977fed37206f6e333226a3b604fac_ddr4%20eccreg%20crucial%2032gb%20(2666)%20cl19%20(ct32g4rfd4266)_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/uploads/product/p_14394/2017/11/07/14394.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/d/d/ddr4-adata-xpg-gammix-d10-black-1.png");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_39_153.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_45_96.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/ram/1703006/000b5ee1b9160ad0a470e6f7f7128fe3_ram%20kingmax%20zeus%20dragon%20heatsink_black_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/ram/1809387/87bc325279ad80083d05704275541cb6_crucial%20ballistix%20sport%20lt_1-pack_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/ram/1806047/d3fc1d173c95e985d73a17b3624b5f25_ram%20kingmax%20zeus%20dragon%20heatsink_red_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/ram/19010422/3d191fef96a5f3a46eab012d22b5973c_adata%20spectrix%20d80%20ddr4%20rgb%20liquid%20cooling_black_x1_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/r/a/ram-apacer-panther-golden-1_1.jpg");
        }};

        for (int i = 0; i < 265; i++) {
            String category = "Ram";
            long price = random.nextInt(6500) * 1000 + 200000;
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
            String img = image.get(random.nextInt(image.size()));

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
            productService.saveProduct(name, category, price, discount, quantity, img, null, fieldDetails);
        }
    }
}
