package com.example.jpa.controllers;

import com.example.jpa.models.entity.Cliente;
import com.example.jpa.service.IClienteService;
import com.example.jpa.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/listar")
    public String getClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model){

        var pageRequest = PageRequest.of(page,4);
        var clientes = clienteService.findAll(pageRequest);
        var pageRender =  new PageRender<>("/listar", clientes);

        model.addAttribute("titulo", "JPA");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page",pageRender);
        return "listar";
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("titulo", "Formulario");
        model.put("cliente", cliente);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, RedirectAttributes flash, SessionStatus sessionStatus){

        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        String mensajeFlash = (cliente.getId() != null)? "CLiente editado con exito" : "Cliente creado con exito";
        clienteService.save(cliente);
        sessionStatus.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @GetMapping("form/{id}")
    public String editar(@PathVariable long id, Model model, RedirectAttributes flash){

        Cliente cliente = new Cliente();

        if(id>0){
            cliente = clienteService.findOne(id);
            if(cliente == null){
                flash.addFlashAttribute("error", "El id del cliente no existe");
                return "redirect:/listar";
            }
        }else{
            flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
            return "redirect:/listar";
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar cliente");

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes flash){
        if(id>0){
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Se elimino con exito");
        }
        return "redirect:/listar";
    }

}
