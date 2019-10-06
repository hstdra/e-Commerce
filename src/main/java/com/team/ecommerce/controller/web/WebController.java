package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.other.VnCurrency;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/web")
public class WebController {
    @Autowired
    private ProductService productService;
    @Autowired
    private VnCurrency vnCurrency;
    @RequestMapping("")
    public String shopGrid
            (Model model,
             @RequestParam(name = "page", defaultValue = "0") Integer page,
             @RequestParam(name = "size", defaultValue = "12") Integer size,
             @RequestParam(name = "sort", defaultValue = "ASC") String sort
            ) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> productPage = productService.getPaginatedProducts(pageable);

        model.addAttribute("totalPage", IntStream.range(0, productPage.getTotalPages()).boxed().collect(Collectors.toList()));
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("products", productService.getAll(pageable));
        model.addAttribute("VnCurrency", vnCurrency);
        return "web/shop-grid";
    }
}
