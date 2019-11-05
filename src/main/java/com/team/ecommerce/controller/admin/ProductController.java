package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Category;
import com.team.ecommerce.entity.FieldDetail;
import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.CategoryService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    // Import new product //
    @RequestMapping("chooseCategory")
    public String listAllCategory(Model model) {
        model.addAttribute("categories", categoryService.listAll());
        return "admin/product/chooseCategory";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Product product, @RequestParam String fds) {
        try {
            Map<String, String> fieldDetails = new HashMap<>();
            String[] details = fds.split(";;;");
            Category category = categoryService.getOne(product.getCategory().getId());
            for (int i = 0; i < category.getFields().size(); i++) {
                fieldDetails.put(category.getFields().get(i).getField(), details[i]);
            }
            productService.editProduct(product, fieldDetails);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(Model model, @RequestParam("getCategoryId") Integer categoryId) {
        Product product = new Product();
        Category category = categoryService.getOne(categoryId);
        product.setCategory(category);
        product.setFieldDetails(new LinkedList<>());
        category.getFields().forEach(f -> {
            FieldDetail fieldDetail = new FieldDetail();
            fieldDetail.setField(f);
            product.getFieldDetails().add(fieldDetail);
        });

        model.addAttribute("fields", category.getFields());
        model.addAttribute("product", product);
        return "admin/product/add";
    }

    @RequestMapping(value = "saveAdd", method = RequestMethod.POST)
    public String save(@ModelAttribute Product product, @RequestParam String fds) {
        try {
            Map<String, String> fieldDetails = new HashMap<>();
            String[] details = fds.split(";;;");
            Category category = categoryService.getOne(product.getCategory().getId());
            for (int i = 0; i < category.getFields().size(); i++) {
                fieldDetails.put(category.getFields().get(i).getField(), details[i]);
            }
            productService.saveProduct(product.getName(), product.getCategory().getCategory(), product.getPrice(), product.getDiscount(), 0, null, product.getDescription(), fieldDetails);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin/product";
    }

}
