package com.example.errores.controllers;

import com.example.errores.models.domain.Usuario;
import com.example.errores.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Model model){
        //Integer algo = 100/0;
        Integer algo = Integer.parseInt("10xc");
        return "index";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model){

        Usuario usuario = usuarioService.getUsuarioForId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle del usuario: ".concat(usuario.getNombre()));
        return "ver";
    }
}
