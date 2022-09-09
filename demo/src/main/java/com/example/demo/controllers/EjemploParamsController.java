package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/params")
@Controller
public class EjemploParamsController {

    @GetMapping("/")
    public String index(){
        return "params/index";
    }

    @GetMapping("/string")
    public String param(@RequestParam(name = "texto", defaultValue = "no existe algo")String texto ,Model model){
        model.addAttribute("resultado", "El texto del parametro es: ".concat(texto));
        return "params/ver";
    }
}
