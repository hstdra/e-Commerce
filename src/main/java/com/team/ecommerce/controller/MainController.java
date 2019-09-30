package com.team.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;

@Controller
public class MainController {
    @Autowired
    private EntityManager entityManager;

    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }


}
