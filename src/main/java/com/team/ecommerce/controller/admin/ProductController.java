package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.CategoryService;
import com.team.ecommerce.service.HistotyService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String manage(Model model){
        model.addAttribute("products",productService.getAll());
        return "admin/product/manage";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("product", productService.get(id));
        return "admin/product/edit";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            productService.delete(id);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("Product") Product product) {
        try {
            productService.save(product);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/product";
    }

}
