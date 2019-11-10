package com.team.ecommerce.controller.web;

import com.team.ecommerce.entity.Order;
import com.team.ecommerce.entity.OrderDetail;
import com.team.ecommerce.entity.Product;
import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.MoMoService;
import com.team.ecommerce.service.OrderService;
import com.team.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.Instant;

@Controller
@RequestMapping("/web/order")
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MoMoService moMoService;

    @RequestMapping("history")
    public String orderHistory(Model model, HttpSession session) {
        if (session.getAttribute("customer") == null)
            return "web/shop-grid";
        int userId = ((User) session.getAttribute("customer")).getId();
        model.addAttribute("orders", orderService.getOrderHistoryByUser(userId));
        return "web/order-history";
    }

    @RequestMapping("check-out")
    public String checkOut(Model model, HttpSession session) {
        Order cart = (Order) session.getAttribute("cart");
        if (cart.getOrderDetails().isEmpty())
            return "web/shop-grid";
        model.addAttribute("order", cart);
        return "web/check-out";
    }

    @RequestMapping("/{id}")
    public String orderDetail(Model model, HttpSession session, @PathVariable int id) {
        if (session.getAttribute("customer") == null)
            return "web/shop-grid";
        model.addAttribute("order", orderService.getOrder(id));
        return "web/order-detail";
    }

    @RequestMapping("repayment/{id}")
    public String repayment(HttpSession session, @PathVariable int id) {
        if (session.getAttribute("customer") == null)
            return "web/shop-grid";
        String returnUrl = moMoService.getMoMoPayUrl(orderService.getOrder(id));
        return returnUrl.isEmpty() ? "web/shop-grid" : returnUrl;
    }

    @RequestMapping("cancel/{id}")
    public String cancel(HttpSession session, @PathVariable int id) {
        if (session.getAttribute("customer") == null)
            return "web/shop-grid";
        orderService.cancelOrder(id);
        return "redirect:/web/order/" + id;
    }

    @PostMapping("process")
    public String processCheckOut(@ModelAttribute Order order, HttpSession session) {
        if (session.getAttribute("customer") == null)
            return "web/shop-grid";

        Order cart = (Order) session.getAttribute("cart");
        cart.setName(order.getName());
        cart.setPhone(order.getPhone());
        cart.setAddress(order.getAddress());
        cart.setStatus(order.getStatus());
        cart.setDate(Date.from(Instant.now()));
        orderService.saveOrder(cart);
        for (OrderDetail orderDetail : cart.getOrderDetails()) {
            Product product = orderDetail.getProduct();
            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            productService.save(product);
        }
        session.setAttribute("cart", orderService.createShopCart(cart.getUser().getId()));

        String returnUrl = moMoService.getMoMoPayUrl(cart);
        return returnUrl == null ? "redirect:/web/order/" + cart.getId() : returnUrl;
    }
}
