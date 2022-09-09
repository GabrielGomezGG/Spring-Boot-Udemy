package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("titulo","Bienvenido");
        return "index";
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        Usuario usuario = new Usuario();
        usuario.setApellido("Pepe");
        usuario.setNombre("Aron");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo","Bienvenido: ".concat(usuario.getNombre()));
        return "perfil";
    }
}
