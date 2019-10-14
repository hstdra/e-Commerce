package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Field;
import com.team.ecommerce.service.CategoryService;
import com.team.ecommerce.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/field")
public class FieldController {
    @Autowired
    private FieldService service;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("add/{category_id}")
    public String add(Model model, @PathVariable Integer category_id ) {
        model.addAttribute("field", new Field());
        return "admin/field/add";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("field", service.getById(id));
        return "admin/field/edit";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        int category_id = service.getById(id).getCategory().getId();
        try {
            service.delete(id);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/category/edit/" + category_id;
    }

    @RequestMapping(value = "save/{category_id}", method = RequestMethod.POST)
    public String save(@ModelAttribute("Field") Field field, @PathVariable Integer category_id) {
        field.setCategory(categoryService.getOne(category_id));
        try {
            service.save(field);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/category/edit/" + field.getCategory().getId();
    }
}
