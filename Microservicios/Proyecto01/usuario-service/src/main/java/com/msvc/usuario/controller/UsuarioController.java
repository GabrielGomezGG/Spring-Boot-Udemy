package com.msvc.usuario.controller;

import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioRequest){
        var usuario = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    
    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllUsuarios (){
        var usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name="ratingHotelFallback", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable String id){
        var usuario = usuarioService.getUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Usuario> ratingHotelFallback(String usuarioId, Exception exception){
        var usuario = Usuario.builder()
                .nombre("root")
                .email("asasd@gmail.com")
                .informacion("Se cayo el sirvicio")
                .id("1234")
                .build();
        return ResponseEntity.ok(usuario);
    }

}
