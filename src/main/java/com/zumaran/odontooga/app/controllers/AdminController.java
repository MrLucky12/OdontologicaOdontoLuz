package com.zumaran.odontooga.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("user", "Zumaran");
        return "admin/index";
    }
}
