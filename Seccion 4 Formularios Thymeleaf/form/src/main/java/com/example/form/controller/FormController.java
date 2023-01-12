package com.example.form.controller;

import com.example.form.models.domain.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Formulario");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(
            @Valid Usuario usuario,
            BindingResult result,
            Model model
    ) {
        model.addAttribute("titulo", "Resultado formulario");

        if(result.hasErrors()){
            /*
            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(err -> {
                errores.put(
                        err.getField(),
                        "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage())
                );
            });
            model.addAttribute("error", errores);
            */
            return "form";
        }

        model.addAttribute("usuario", usuario);
        return "respuesta";
    }

}
