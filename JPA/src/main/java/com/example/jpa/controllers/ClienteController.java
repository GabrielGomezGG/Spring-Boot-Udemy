package com.example.jpa.controllers;

import com.example.jpa.models.dao.IClienteDao;
import com.example.jpa.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
