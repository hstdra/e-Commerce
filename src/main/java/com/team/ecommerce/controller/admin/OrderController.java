package com.team.ecommerce.controller.admin;

import com.team.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @RequestMapping("")
    public String manage(Model model) {
//        model.addAttribute()
        return null;
    }
}
