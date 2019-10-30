package com.team.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        System.out.println(request.getHeader("Host"));
        return "redirect:http://google.com";
    }
}
