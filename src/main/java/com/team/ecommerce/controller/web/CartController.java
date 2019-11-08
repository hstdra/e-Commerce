package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.service.OrderDetailService;
import com.team.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/web/cart")
public class CartController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("add/{product_id}")
    public String addToCart(@PathVariable Integer product_id, HttpSession session, HttpServletRequest request) {
        Order cart = (Order) session.getAttribute("cart");
        session.setAttribute("previous", request.getHeader("referer"));
        orderDetailService.addProductToCart(product_id, cart);
        return "redirect:/web/cart/update";
    }

    @RequestMapping("minus/{product_id}")
    public String minusFromCart(@PathVariable Integer product_id, HttpSession session, HttpServletRequest request) {
        Order cart = (Order) session.getAttribute("cart");
        session.setAttribute("previous", request.getHeader("referer"));
        orderDetailService.minusProductFromCart(product_id, cart);
        return "redirect:/web/cart/update";
    }

    @RequestMapping("delete/{product_id}")
    public String deleteFromCart(@PathVariable Integer product_id, HttpSession session, HttpServletRequest request) {
        Order cart = (Order) session.getAttribute("cart");
        orderDetailService.deleteProductFromCart(product_id, cart);
        session.setAttribute("previous", request.getHeader("referer"));
        return "redirect:/web/cart/update";
    }

    @RequestMapping("update")
    public String updateShopCart(HttpSession session) {
        try {
            Order cart = (Order) session.getAttribute("cart");
            session.setAttribute("cart", orderService.getShopCart(cart.getUser().getId()));
        } catch (Exception ignored) {
        }
        return "redirect:" + session.getAttribute("previous");
    }


}
