package com.example.horariointerceptor.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("${confir.horario.apertura}")
    private Integer apertura;

    @Value("${confir.horario.cierre}")
    private Integer cierre;

    @GetMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("titulo","Bienvenido al horario de atencion al cliente!");
        return "index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model){

        StringBuilder mensaje = new StringBuilder("Cerrado. El horario de atencion es de ")
                .append(apertura).append(" hasta las ").append(cierre);

        model.addAttribute("titulo","Fuera del horario de atencion");
        model.addAttribute("mensaje",mensaje.toString());
        return "cerrado";
    }

}
