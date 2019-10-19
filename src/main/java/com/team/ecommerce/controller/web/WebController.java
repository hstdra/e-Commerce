package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.LuceneSearchService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
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
             @RequestParam(name = "size", defaultValue = "36") Integer size,
             @RequestParam(name = "ct", defaultValue = "") String ct,
             @RequestParam(name = "fds", defaultValue = "") String fds,
             @RequestParam(name = "sort", defaultValue = "ASC") String sort
            ) {
        List<Product> products = luceneSearchService.search(q, ct, fds);

        LinkedHashMap<String, Integer> categories = luceneSearchService.getCategory(q, ct, fds);
        System.out.println(categories);

        model.addAttribute("totalPage", IntStream.range(0, 1 + products.size() / size).boxed().collect(Collectors.toList()));
        model.addAttribute("page", page);
        model.addAttribute("products", products.subList(Math.min(page * size, products.size()), Math.min(++page * size, products.size())));
        return "web/shop-grid";
    }
}
