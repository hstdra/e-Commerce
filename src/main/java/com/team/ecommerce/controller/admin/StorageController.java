package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Category;
import com.team.ecommerce.entity.History;
import com.team.ecommerce.entity.Product;
import com.team.ecommerce.service.CategoryService;
import com.team.ecommerce.service.HistotyService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/storage")
public class StorageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HistotyService histotyService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String listAll(Model model){
        model.addAttribute("products", productService.getAll());
        return "admin/storage/manage";
    }

    // Import new product //
    @RequestMapping("chooseCategory")
    public String listAllCategory(Model model){
        model.addAttribute("categories", categoryService.listAll());
        return "admin/storage/chooseCategory";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(Model model, @RequestParam("getCategoryId") Integer categoryId){
        Product product = new Product();
        Category category = categoryService.getOne(categoryId);
        product.setCategory(category);
        System.out.println(IntStream.range(0, category.getFields().size()).boxed().collect(Collectors.toList()));
        model.addAttribute("nums", IntStream.range(0, category.getFields().size()).boxed().collect(Collectors.toList()));
        model.addAttribute("product", product);
        model.addAttribute("fields", category.getFields());
        return "admin/storage/add";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Product product){
        try {
            productService.createProduct(product.getName(), product.getCategory().getCategory(), product.getPrice(), product.getDiscount(), 0, null, new HashMap<>());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin/storage";
    }

    // Saving History And Change Product Quantity//
    @RequestMapping("import/{id}")
    public String imp(Model model, @PathVariable Integer id){
        model.addAttribute("product",productService.get(id));
        return "admin/storage/import";
    }

    @RequestMapping("export/{id}")
    public String exp(Model model, @PathVariable Integer id){
        model.addAttribute("product",productService.get(id));
        return "admin/storage/export";
    }

    @RequestMapping(value = "impSave", method = RequestMethod.POST)
    public String impSave(@ModelAttribute("Product") Product product,
                        @RequestParam("changeQuantity") String changeQuanity,
                        @RequestParam("histotyDescription") String histotyDescription) {
        int temp = 0;
        try {
            temp = Integer.parseInt(changeQuanity);
            if(temp < 0) System.out.println("false");
            else{
                History history = new History();
                history.setDescription(histotyDescription);
                history.setDate(new Date());
                history.setProduct(product);
                history.setQuantity(temp);
                product.setQuantity(product.getQuantity()+ temp);
                histotyService.save(history);
                productService.save(product);
            }
        } catch (Exception ignored) {
        }
        return "redirect:/admin/storage";
    }

    @RequestMapping(value = "expSave", method = RequestMethod.POST)
    public String empSave(@ModelAttribute("Product") Product product,
                          @RequestParam("changeQuantity") String changeQuanity,
                          @RequestParam("histotyDescription") String histotyDescription) {
        int temp = 0;
        try {
            temp = Integer.parseInt(changeQuanity);
            if(temp > product.getQuantity()) System.out.println("false");
            else{
                History history = new History();
                history.setDescription(histotyDescription);
                history.setDate(new Date());
                history.setProduct(product);
                history.setQuantity(-temp);
                product.setQuantity(product.getQuantity()- temp);
                histotyService.save(history);
                productService.save(product);
            }
        } catch (Exception ignored) {
        }
        return "redirect:/admin/storage";
    }

}
