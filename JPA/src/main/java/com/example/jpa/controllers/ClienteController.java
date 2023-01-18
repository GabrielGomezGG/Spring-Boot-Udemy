package com.example.jpa.controllers;

import com.example.jpa.models.dao.IClienteDao;
import com.example.jpa.models.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
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
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, SessionStatus sessionStatus){

        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }

        clienteDao.save(cliente);
        sessionStatus.setComplete();
        return "redirect:listado";
    }

    @GetMapping("form/{id}")
    public String editar(@PathVariable long id, Model model){

        Cliente cliente = new Cliente();

        if(id>0){
            cliente = clienteDao.findOne(id);
        }else{
            return "redirect:/listado";
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar cliente");

        return "form";
    }

}
