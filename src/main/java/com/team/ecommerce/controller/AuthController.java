package com.team.ecommerce.controller;

import com.team.ecommerce.entity.User;
import com.team.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping("/web/login")
    public String customerLogin(@RequestParam(required = false) String message, Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            } else if (message.equals("signup")) {
                model.addAttribute("message", "Signup Success!");
            }
        } else
            model.addAttribute("message", "");
        return "web/login";
    }

    @RequestMapping("/web/signup")
    public String customerSignup(@RequestParam(required = false) String message, Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("email")) {
                model.addAttribute("message", "Email already existed!");
            }
        } else
            model.addAttribute("message", "");
        model.addAttribute("customer", new User());
        return "web/signup";
    }

    @PostMapping("/web/signup_process")
    public String signupProcess(@ModelAttribute User customer) {
        customer.setPassword(encoder.encode(customer.getPassword()));
        customer.setRole("ROLE_CUSTOMER");
        try {
            userService.save(customer);
            return "redirect:/web/login?message=signup";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/web/signup?message=email";
        }
    }

    @RequestMapping("/web/login_success")
    public String loginSuccess(HttpSession session) {
        User user = (User) session.getAttribute("customer");
        if (user.getRole().equals("ROLE_ADMIN"))
            return "redirect:/admin";
        else return "redirect:/web";
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