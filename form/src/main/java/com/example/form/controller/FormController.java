package com.example.form.controller;

import com.example.form.editors.NombreMayusculaEditor;
import com.example.form.editors.PaisPropertiesEditor;
import com.example.form.editors.RolesEditor;
import com.example.form.models.domain.Pais;
import com.example.form.models.domain.Role;
import com.example.form.models.domain.Usuario;
import com.example.form.services.PaisService;
import com.example.form.services.RoleService;
import com.example.form.validation.UsuarioValidador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertiesEditor paisPropertiesEditor;

    @Autowired
    private RolesEditor rolesEditor;

    @Autowired
    private RoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.addValidators(validador);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));

        binder.registerCustomEditor(String.class, "nombre",new NombreMayusculaEditor());
        binder.registerCustomEditor(Pais.class, "pais", paisPropertiesEditor);
        binder.registerCustomEditor(Role.class, "roles", rolesEditor);
    }

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Titi");
        usuario.setApellido("Pepep");
        usuario.setId("23.321.321-A");
        usuario.setHabilitar(true);
        usuario.setCuenta(5);
        usuario.setEmail("a@g.com");
        usuario.setPassword("asddsa");
        usuario.setUsername("tiutu");
        usuario.setValorSecreto("aaaaaaaaaaaaaaaaa");
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

        if(result.hasErrors()){
            model.addAttribute("titulo", "Resultado formulario");
            return "form";
        }

        return "redirect:/ver";
    }

    @GetMapping("/ver")
    public String ver(
            @SessionAttribute(name="usuario", required = false) Usuario usuario,
            Model model,
            SessionStatus status
    ){
        if(usuario == null){
            return "redirect:/form";
        }
        model.addAttribute("titulo", "Resultado formulario");
        status.setComplete();
        return "respuesta";
    }

    @ModelAttribute("paises")
    public List<String> paises(){
        return Arrays.asList("Argentina", "Chile", "Maxico");
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises(){
        return paisService.listar();
    }

    @ModelAttribute("paisesMap")
    public Map<String,String> paisesMap(){
        Map<String,String> paises = new HashMap<String,String>();
        paises.put("AR", "Argentina");
        paises.put("CH", "Chile");
        paises.put("MX", "Maxico");

        return paises;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String,String> listaRolesMap(){
        Map<String,String> roles = new HashMap<String,String>();
        roles.put("ROLE_ADMIN", "ADMINISTRADOR");
        roles.put("ROLE_USER", "USUARIO");
        roles.put("ROLE_MODERATOR", "MODERADOR");

        return roles;
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString(){
        return Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR");
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles(){
        return roleService.getRoles();
    }

    @ModelAttribute("generos")
    public List<String> genero(){
        return Arrays.asList("Hombre","Mujer");
    }
}
