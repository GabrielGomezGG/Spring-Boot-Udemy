package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class EjemploVariableRutaController {

    @GetMapping("/")
    public String index(Model model){
        return "variables/index";
    }

    @GetMapping("/string/{texto}")
    public String variables(@PathVariable String texto, Model model){
        model.addAttribute("resultado", "El resultado es: "+texto);
        return "variables/ver";
    }

    @GetMapping("/string/{texto}/{numero}")
    public String variables(@PathVariable String texto, @PathVariable Integer numero, Model model){
        model.addAttribute("resultado", "El resultado es: "+texto + " y el numero es: " + numero);
        return "variables/ver";
    }
}
