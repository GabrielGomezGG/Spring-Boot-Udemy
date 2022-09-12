package com.example.demo_di.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("titulo", "Este es un ejemplo de controller y IDP");
        return "index";
    }
}
