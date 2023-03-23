package com.example.demo.controllers;

import com.example.demo.models.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;

    @Value("${texto.indexcontroller.perfil.titulo}")
    private String textoPerfil;

    @Value("${texto.indexcontroller.listado.titulo}")
    private String textoListado;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("titulo",textoIndex);
        return "index";
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        Usuario usuario = new Usuario();
        usuario.setApellido("Pepe");
        usuario.setNombre("Aron");
        usuario.setEmail("@gasf.com");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo",textoPerfil.concat(usuario.getNombre()));
        return "perfil";
    }

    @GetMapping("/listado")
    public String listado(Model model){
        /*List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setApellido("Pepe");
        usuario.setNombre("Aron");
        usuario.setEmail("@gasf.com");

        Usuario usuario2 = new Usuario();
        usuario2.setApellido("Pepe");
        usuario2.setNombre("Aron");
        usuario2.setEmail("@gasf.com");

        Usuario usuario3 = new Usuario();
        usuario3.setApellido("Pepe");
        usuario3.setNombre("Aron");
        usuario3.setEmail("@gasf.com");

        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario);

        model.addAttribute("usuarios", usuarios);*/
        model.addAttribute("titulo",textoListado);
        return "listado";
    }

    @ModelAttribute("usuarios")
    public List<Usuario> usuarios (){
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Titi", "Toto", "Tutu"),
                new Usuario("Titi", "Toto", "Tutu"),
                new Usuario("Titi", "Toto", "Tutu"),
                new Usuario("Titi", "Toto", "Tutu")
        );
        return usuarios;
    }
}
