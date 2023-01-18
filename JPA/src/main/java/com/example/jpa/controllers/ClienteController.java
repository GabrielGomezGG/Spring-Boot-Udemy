package com.example.jpa.controllers;

import com.example.jpa.models.dao.IClienteDao;
import com.example.jpa.models.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @GetMapping("/listado")
    public String getClientes(Model model){
        model.addAttribute("clientes", clienteDao.findAll());
        model.addAttribute("titulo", "JPA");
        return "listado";
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("titulo", "Formulario");
        model.put("cliente", cliente);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }

        clienteDao.save(cliente);
        return "redirect:listado";
    }

}
