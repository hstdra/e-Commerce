package com.team.ecommerce.controller.admin;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.service.OrderService;
import com.team.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String manage(Model model) {
        model.addAttribute("orders", service.getAllOrder());
        return "admin/order/manage";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("order", service.getOrder(id));
        return "admin/order/edit";
    }

    @RequestMapping(value = "save_info", method = RequestMethod.POST)
    public String save(@ModelAttribute("Order") Order orderForm) {
        Order order = service.getOrder(orderForm.getId());
        order.setName(orderForm.getName());
        order.setAddress(orderForm.getAddress());
        order.setPhone(orderForm.getPhone());
        order.setStatus(orderForm.getStatus());

        try {
            service.saveOrder(order);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/order/";
    }

}
