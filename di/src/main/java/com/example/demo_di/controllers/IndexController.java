package com.example.demo_di.controllers;

import com.example.demo_di.models.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("MiServicioComplejo")
    private IService miServicio;

    @GetMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("titulo", miServicio.algo());
        return "index";
    }
}
