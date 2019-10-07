package com.team.ecommerce.controller;

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
    private UserService service;

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
            httpSession.setAttribute("customer", service.getByEmail(email));
        }
        return "redirect:/web";
    }

    @RequestMapping("/web/logout_success")
    public String logoutSuccess(HttpSession httpSession) {
        httpSession.setAttribute("customer", null);
        return "redirect:/web";
    }

    @RequestMapping("/403")
    public String accessDenied403() {
        return "403";
    }
}