package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.LuceneSearchService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/web")
public class WebController {
    @Autowired
    private ProductService productService;

    @Autowired
    private LuceneSearchService luceneSearchService;

    @RequestMapping("")
    public String shopGrid
            (Model model,
             @RequestParam(name = "q", defaultValue = "") String q,
             @RequestParam(name = "page", defaultValue = "0") Integer page,
             @RequestParam(name = "size", defaultValue = "32") Integer size,
             @RequestParam(name = "ct", defaultValue = "") String ct,
             @RequestParam(name = "fds", defaultValue = "") String fds,
             @RequestParam(name = "sort", defaultValue = "ASC") String sort
            ) {
        List<Product> products = luceneSearchService.search(q, ct, fds);

//        if (ct != 0)
//            products = products.stream().filter(p -> ct.equals(p.getCategory().getId())).collect(Collectors.toList());
//
//        if (!fieldDetails.isEmpty()){
//            for (String fd : fieldDetails.split(";;")) {
//                String[] temp = fd.split("::");
//                for (int i = 0; i < products.size(); i++) {
//                    Product p = products.get(i);
//                    boolean isRemove = true;
//                    for (FieldDetail f : p.getFieldDetails()) {
//                        if (f.getField().getField().equalsIgnoreCase(temp[0])) {
//                            if (f.getDetail().equalsIgnoreCase(temp[1])) {
//                                isRemove = false;
//                                break;
//                            }
//                        }
//                    }
//                    if (isRemove) {
//                        products.remove(i);
//                        i--;
//                    }
//                }
//            }
//        }
//
//        Map<Category, Integer> categoryIntegerMap = products
//                .stream().collect(Collectors.toMap(Product::getCategory, product -> 1, Integer::sum))
//                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//        Map<Field, Map<String, Integer>> fieldMapMap = new HashMap<>();
//        if (!categoryIntegerMap.isEmpty()) {
//            fieldMapMap = categoryIntegerMap.entrySet().stream()
//                    .findFirst().get().getKey().getFields()
//                    .stream().collect(Collectors.toMap(field -> field, field -> new HashMap<>(), (a, b) -> b));
//        }
//
//
//        for (Product product : products) {
//            for (FieldDetail fieldDetail : product.getFieldDetails()) {
//                if (fieldMapMap.get(fieldDetail.getField()).containsKey(fieldDetail.getDetail()))
//                    fieldMapMap.get(fieldDetail.getField()).put(fieldDetail.getDetail(), fieldMapMap.get(fieldDetail.getField()).get(fieldDetail.getDetail()) + 1);
//                else
//                    fieldMapMap.get(fieldDetail.getField()).put(fieldDetail.getDetail(), 1);
//            }
//        }
//
//
//        model.addAttribute("categoryIntegerMap", categoryIntegerMap);
//        model.addAttribute("fieldMapMap", fieldMapMap);
        model.addAttribute("totalPage", IntStream.range(0, 1 + products.size() / size).boxed().collect(Collectors.toList()));
        model.addAttribute("page", page);
        model.addAttribute("products", products.subList(Math.min(page * size, products.size()), Math.min(++page * size, products.size())));
        return "web/shop-grid";
    }
}
