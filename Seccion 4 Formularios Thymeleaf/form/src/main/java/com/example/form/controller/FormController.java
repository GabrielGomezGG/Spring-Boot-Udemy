package com.example.form.controller;

import com.example.form.editors.NombreMayusculaEditor;
import com.example.form.models.domain.Usuario;
import com.example.form.validation.UsuarioValidador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.addValidators(validador);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));

        binder.registerCustomEditor(String.class, "nombre",new NombreMayusculaEditor());
    }

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Titi");
        usuario.setApellido("Pepep");
        usuario.setId("23.321.321-A");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Formulario");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(
            @Valid Usuario usuario,
            BindingResult result,
            Model model,
            SessionStatus status
    ) {
        //validador.validate(usuario,result);
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
        status.setComplete();
        return "respuesta";
    }

}
