package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.MoMoService;
import com.team.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/web/order")
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private MoMoService moMoService;

    @RequestMapping("history")
    public String orderHistory(Model model, HttpSession session) {
        int userId = ((User) session.getAttribute("customer")).getId();
        model.addAttribute("orders", orderService.getOrderHistoryByUser(userId));
        return "web/order-history";
    }

    @RequestMapping("check-out")
    public String checkOut(Model model, HttpSession session) {
        Order cart = (Order) session.getAttribute("cart");
        if (cart.getOrderDetails().isEmpty())
            return "web";
        model.addAttribute("order", cart);
        return "web/check-out";
    }

    @PostMapping("process")
    public String processCheckOut(@ModelAttribute Order order, HttpSession session, HttpServletRequest request) {
        Order cart = (Order) session.getAttribute("cart");
        cart.setName(order.getName());
        cart.setPhone(order.getPhone());
        cart.setAddress(order.getAddress());
        cart.setStatus(order.getStatus());
        orderService.saveOrder(cart);
        session.setAttribute("cart", orderService.createShopCart(cart.getUser().getId()));
        if (cart.getStatus() == 10) {
            try {
                String returnUrl = "http://google.com";
                return "redirect:" + moMoService.captureMoMoResponse(cart, returnUrl).getPayUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "web/shop-grid";
    }
}
