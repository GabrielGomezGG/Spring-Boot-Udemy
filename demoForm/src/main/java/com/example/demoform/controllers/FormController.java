package com.example.demoform.controllers;

import com.example.demoform.models.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("titulo", "Formulario usuario");
        Usuario usuario = new Usuario();
        usuario.setId("ASDASD");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status){

        model.addAttribute("titulo", "Resultado usuario");
        if(result.hasErrors()){
//            Map<String, String> errores = new HashMap<>();
//            result.getFieldErrors().forEach(err ->{
//                errores.put(err.getField(), "El campo"+err.getField()+" "+err.getDefaultMessage());
//            });
//            model.addAttribute("error", errores);

            return "form";

        }
        model.addAttribute("usuario", usuario);
        status.setComplete();
        return "resultado";
    }
}
