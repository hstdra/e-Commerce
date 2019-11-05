package com.team.ecommerce.controller.admin;

import com.team.ecommerce.service.HistotyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/history")
public class HistoryController {

    @Autowired
    private HistotyService service;

    @RequestMapping("")
    public String getAll(Model model){
        model.addAttribute("histories",service.getAll());
        return "admin/history/history";
    }
}
