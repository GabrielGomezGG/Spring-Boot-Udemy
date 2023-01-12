package com.example.form.controller;

import com.example.form.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("titulo", "Formulario");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(
            Model model,
            String username,
            String password,
            String email) {

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        model.addAttribute("titulo", "Resultado formulario");
        model.addAttribute("usuario", usuario);
        return "respuesta";
    }

}
