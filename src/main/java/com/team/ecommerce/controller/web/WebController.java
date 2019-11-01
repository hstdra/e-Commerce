package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.LuceneSearchService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
             @RequestParam(name = "size", defaultValue = "24") Integer size,
             @RequestParam(name = "ct", defaultValue = "") String ct,
             @RequestParam(name = "fds", defaultValue = "") String fds,
             @RequestParam(name = "from", defaultValue = "") String from,
             @RequestParam(name = "to", defaultValue = "") String to,
             @RequestParam(name = "sort", defaultValue = "ID_DESC") String sort
            ) {
        List<Product> products = luceneSearchService.search(q, ct, fds, from, to, sort);

        LinkedHashMap<String, Integer> categories = luceneSearchService.getCategory(q, ct, fds, from, to);
        if (!q.isEmpty() || !ct.isEmpty()) {
            LinkedHashMap<String, LinkedHashMap<String, Integer>> fieldDetails = luceneSearchService.getFieldDetails(q, ct, fds, from, to);
            model.addAttribute("fieldDetails", fieldDetails);
        }

        if (page < 0) page = 0;
        if (page > products.size() / size) page = products.size() / size;

        model.addAttribute("categories", categories);
        model.addAttribute("totalPage", IntStream.range(Math.max(0, page - 2), Math.min(1 + products.size() / size, page + 3)).boxed().collect(Collectors.toList()));
        model.addAttribute("page", page);
        model.addAttribute("products", products.subList(Math.min(page * size, products.size()), Math.min(++page * size, products.size())));
        return "web/shop-grid";
    }

    @RequestMapping("product/{id}")
    public String singleProduct(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productService.get(id));
        return "web/single-product";
    }

}
