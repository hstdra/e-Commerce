package com.team.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @RequestMapping("/web/login")
    public String customerLogin(@RequestParam(required = false) String message, Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            }
        }
        return "web/login";
    }

    @RequestMapping("/web/login_success")
    public String loginSuccess() {
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