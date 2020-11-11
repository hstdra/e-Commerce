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

        for (int i = 0; i < 1; i++) {
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

    @Test
    public void testCreateProduct2() {
        ArrayList<String> bus = new ArrayList<String>() {{
            add("192-bit");
            add("256-bit");
            add("352-bit");
            add("128-bit");
        }};
        ArrayList<String> hang = new ArrayList<String>() {{
            add("GALAX");
            add("ASUS");
            add("NVIDIA");
            add("MSI");
            add("GIGABYTE");
        }};
        ArrayList<String> vram = new ArrayList<String>() {{
            add("6GB");
            add("4GB");
            add("8GB");
            add("11GB");
        }};
        ArrayList<String> quat = new ArrayList<String>() {{
            add("Một");
            add("Hai");
            add("Ba");
        }};
        ArrayList<String> led = new ArrayList<String>() {{
            add("400W");
            add("500W");
            add("600W");
            add("800W");
        }};
        ArrayList<String> gen = new ArrayList<String>() {{
            add("GDDR5");
            add("GDDR5X");
            add("GDDR6");
        }};
        ArrayList<String> chip = new ArrayList<String>() {{
            add("GTX 1050");
            add("GTX 1060");
            add("GTX 1070");
            add("GTX 1080");
            add("GTX 1080Ti");
            add("GTX 1660Ti");
            add("RTX 2060");
            add("RTX 2070");
            add("RTX 2080");
            add("RTX 2080Ti");
        }};
        ArrayList<String> image = new ArrayList<String>() {{
            add("https://img.vnshop.vn/height/350/media/catalog_v2/media/42/84/1567064528.2475314_Asus_Rog_Matrix_GeForce_RTX_2080_Ti_Infinity_Loop_11GB_GDDR6_ROG-MATRIX-RTX2080TI-P11G-GAMING_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/card_do_hoa/19040900/fe7d614998c940e006f60be061ad2ede_galax%20rtx%202080%20ti%20hof%2011gb%20gddr6%20(28iulbucv6dh)_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog_v2/media/14/89/1566974022.2271876_Asus_Rog_Strix_GeForce_RTX_2080_Ti_OC_edition_11GB_GDDR6_ROG-STRIX-RTX2080TI-O11G-GAMING_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/g/i/gigabyte_geforce_rtx_2080_ti_aorus_xtreme_11g_gv-n208taorus_x-11gc__1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/card_do_hoa/19020088/7a7182d93af6ca97be938df54158bdc1_msi%20geforce%20rtx%202080%20ti%20lightning%20z%2011gb%20gddr6_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_39_126.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog_v2/media/1/65/1566961084.4385448_Asus_Rog_Strix_GeForce_RTX_2080_Super_OC_8GB_GDDR6_ROG-STRIX-RTX2080S-O8G-GAMING_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog_v2/media/78/6/1566980557.344678_Asus_ROG_Strix_GTX_1660_Ti_OC_6GB_GDDR6_ROG-STRIX-GTX1660Ti-O6G-GAMING_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/card_do_hoa/19040024/133463484d4b0e027abf813acecaad6c_card-m%C3%A0n-h%C3%ACnh-vga-gigabyte-aorus-geforce-gtx-1660-ti-6g-(gv-n166taorus-6gd)-1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_54_19.jpg");
        }};

        for (int i = 0; i < 1; i++) {
            String category = "VGA";
            long price = random.nextInt(9000) * 4000 + 1000000;
            long discount = Math.round(price * (100 - random.nextInt(30)) / 100000) * 1000;
            int quantity = 10;
            String b = bus.get(random.nextInt(bus.size()));
            String h = hang.get(random.nextInt(hang.size()));
            String dl = vram.get(random.nextInt(vram.size()));
            String c = chip.get(random.nextInt(chip.size()));
            String ld = led.get(random.nextInt(led.size()));
            String g = gen.get(random.nextInt(gen.size()));
            String q = quat.get(random.nextInt(quat.size()));
            String name = String.format("%s %s %s %s %s", category, h, c, dl, g);
            String img = image.get(random.nextInt(image.size()));

            Map<String, String> fieldDetails = new HashMap<String, String>() {
                {
                    put("Hãng", h);
                    put("Chip đồ họa", c);
                    put("VRAM", dl);
                    put("Công suất nguồn", ld);
                    put("Bus bộ nhớ", b);
                    put("Thế hệ bộ nhớ", g);
                    put("Quạt tản nhiệt", q);
                }
            };
            productService.saveProduct(name, category, price, discount, quantity, img, null, fieldDetails);
        }
    }

    @Test
    public void testCreateProduct3() {
        ArrayList<String> hang = new ArrayList<String>() {{
            add("SAMSUNG");
            add("INTEL");
            add("ADATA");
            add("TRANSCEND");
            add("SEAGATE");
            add("WD");
        }};
        ArrayList<String> dungluong = new ArrayList<String>() {{
            add("128GB");
            add("256GB");
            add("240GB");
            add("480GB");
            add("512GB");
            add("1TB");
            add("2TB");
        }};
        ArrayList<String> kichthuoc = new ArrayList<String>() {{
            add("M.2");
            add("2.5");
            add("3.5");
            add("mSATA");
        }};
        ArrayList<String> chuan = new ArrayList<String>() {{
            add("NVMe");
            add("SATA");
            add("SATA 3");
            add("PCIe");
        }};
        ArrayList<String> speed = new ArrayList<String>() {{
            add("480MB/s-510MB/s");
            add("470MB/s-520MB/s");
            add("500MB/s-510MB/s");
            add("510MB/s-515MB/s");
            add("1330MB/s-1440MB/s");
            add("1570MB/s-1690MB/s");
            add("3210MB/s-3350MB/s");
            add("3100MB/s-3200MB/s");
        }};

        ArrayList<String> image = new ArrayList<String>() {{
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/o_cung/19070616/40114e746def6eceeec84b4a2beb5723_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/m/z/mz-v7p512bw_1_041218-r.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/o_cung/1703297/3f719e0e0432a27f7e052b1d0e077391_ssd%20wd%202tb%20wds200t2b0a_1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/4/_/4_24_110.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/s/s/ssd-m.2-adata-sx8000-1tb-1.png");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/o_cung/1703049/afbfd89bb2116d3dc2b1fcd8fa9ebc49_f480gble200b.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_39_178.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_39_167.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/m/z/mz-v7e500bw_001_front_black_041218-r.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/1/_/1_39_182.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/s/s/ssd_samsung_860_evo_msata_500gb_2.5_mz-m6e500bw__1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog/product/storage/o_cung/1703304/12273e31855927b69ba4beb634184f09_ssdsc2kw360h6x1.jpg");
            add("https://img.vnshop.vn/height/350/media/catalog_v2/media/41/72/1566183207.400105_O_cung_SSD_Kingston_KC2000_500GB_M.2_2280_NVMe_PCIe_SKC2000M8500G_1.jpg");
        }};

        for (int i = 0; i < 1; i++) {
            String category = "SSD";
            long price = random.nextInt(4400) * 1000 + 400000;
            long discount = Math.round(price * (100 - random.nextInt(30)) / 100000) * 1000;
            int quantity = 10;
            String kt = kichthuoc.get(random.nextInt(kichthuoc.size()));
            String h = hang.get(random.nextInt(hang.size()));
            String dl = dungluong.get(random.nextInt(dungluong.size()));
            String c = chuan.get(random.nextInt(chuan.size()));
            String s = speed.get(random.nextInt(speed.size()));

            String name = String.format("%s %s %s %s %s", category, h, dl, kt, c);
            String img = image.get(random.nextInt(image.size()));

            Map<String, String> fieldDetails = new HashMap<String, String>() {
                {
                    put("Hãng", h);
                    put("Dung lượng", dl);
                    put("Kích thước", kt);
                    put("Chuẩn kết nối", c);
                    put("Tốc độ", s);
                }
            };
            productService.saveProduct(name, category, price, discount, quantity, img, null, fieldDetails);
        }
    }

    @Test
    public void tao3() {
        for (int i = 0; i < 200; i++) {
            testCreateProduct();
            testCreateProduct2();
//            testCreateProduct3();
        }
    }
}
