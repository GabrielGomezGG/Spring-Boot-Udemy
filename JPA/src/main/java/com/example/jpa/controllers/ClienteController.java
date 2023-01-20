package com.example.jpa.controllers;

import com.example.jpa.models.entity.Cliente;
import com.example.jpa.service.IClienteService;
import com.example.jpa.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/uploads/{filename:.*}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        var pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
        log.info("pathFoto", pathFoto);
        Resource recurso = null;
        try {
            recurso = new UrlResource(pathFoto.toUri());
            if (!recurso.exists() || !recurso.isReadable()) {
                throw new RuntimeException("Error: no se puede cargar la imagen" + pathFoto);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso + "\"").body(recurso);
    }

    @GetMapping("/ver/{id}")
    public String ver(
            @PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash
    ) {
        var cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la bd");
            return "redirect:/listar";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Detalle del cliente: ".concat(cliente.getNombre()));
        return "ver";
    }

    @GetMapping("/listar")
    public String getClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        var pageRequest = PageRequest.of(page, 4);
        var clientes = clienteService.findAll(pageRequest);
        var pageRender = new PageRender<>("/listar", clientes);

        model.addAttribute("titulo", "JPA");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("titulo", "Formulario");
        model.put("cliente", cliente);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(
            @Valid Cliente cliente,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile foto,
            Model model,
            RedirectAttributes flash,
            SessionStatus sessionStatus
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        if (!foto.isEmpty()) {

            if (cliente.getId() != null
                    && cliente.getId() > 0
                    && cliente.getFoto() != null
                    && cliente.getFoto().length() > 0) {
                var rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
                var archivo = rootPath.toFile();

                if(archivo.exists() && archivo.canRead()){
                    archivo.delete();
                }
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsoltPath = rootPath.toAbsolutePath();

            log.info("rootPath: " + rootPath);
            log.info("rootAbsolutPath: " + rootAbsoltPath);
            try {
                Files.copy(foto.getInputStream(), rootAbsoltPath);
                flash.addFlashAttribute("info", "Ha subido correctamente la foto." + uniqueFileName);
                cliente.setFoto(uniqueFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String mensajeFlash = (cliente.getId() != null) ? "CLiente editado con exito" : "Cliente creado con exito";
        clienteService.save(cliente);
        sessionStatus.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @GetMapping("form/{id}")
    public String editar(@PathVariable long id, Model model, RedirectAttributes flash) {

        Cliente cliente = new Cliente();

        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
            return "redirect:/listar";
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar cliente");

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        if (id > 0) {
            var cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Se elimino con exito");

            var rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
            var archivo = rootPath.toFile();

            if (archivo.exists() && archivo.canRead()) {
                if (archivo.delete()) {
                    flash.addFlashAttribute("info", "Foto: " + cliente.getFoto() + " eliminada con exito!");
                }
            }
        }
        return "redirect:/listar";
    }

}
