package com.team.ecommerce.controller;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.OrderService;
import com.team.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/web/login")
    public String customerLogin(@RequestParam(required = false) String message, final Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            }
        }
        return "web/login";
    }

    @RequestMapping("/web/login_success")
    public String loginSuccess(HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User customer = userService.getByEmail(email);
            Order cart = orderService.getShopCart(customer.getId()) != null ? orderService.getShopCart(customer.getId()) : orderService.createShopCart(customer.getId());

            httpSession.setAttribute("customer", customer);
            httpSession.setAttribute("cart", cart);
        }
        return "redirect:/web";
    }

    @RequestMapping("/web/logout_success")
    public String logoutSuccess(HttpSession httpSession) {
        httpSession.setAttribute("customer", null);
        httpSession.setAttribute("cart", null);
        return "redirect:/web";
    }

    @RequestMapping("/403")
    public String accessDenied403() {
        return "403";
    }
}