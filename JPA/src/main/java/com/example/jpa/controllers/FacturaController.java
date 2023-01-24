package com.example.jpa.controllers;

import com.example.jpa.models.entity.Factura;
import com.example.jpa.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;
    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash){

        var cliente = clienteService.findOne(clienteId);

        if(cliente == null){
            flash.addFlashAttribute("error", "EL cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        var factura = new Factura();
        factura.setCliente(cliente);

        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Crear factura");

        return "factura/form";
    }

}
