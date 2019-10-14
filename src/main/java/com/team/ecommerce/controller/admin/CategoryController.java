package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Category;
import com.team.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @RequestMapping("")
    public String manage(Model model) {
        model.addAttribute("categories", service.listAll());
        return "admin/category/manage";
    }

    @RequestMapping("add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("category", service.getOne(id));
        return "admin/category/edit";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            service.delete(id);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("Category") Category category) {
        try {
            service.save(category);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/category";
    }
}
