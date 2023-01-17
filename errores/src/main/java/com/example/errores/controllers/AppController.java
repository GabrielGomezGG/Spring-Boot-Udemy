package com.example.errores.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/index")
    public String index(Model model){
        //Integer algo = 100/0;
        Integer algo = Integer.parseInt("10xc");
        return "index";
    }
}
