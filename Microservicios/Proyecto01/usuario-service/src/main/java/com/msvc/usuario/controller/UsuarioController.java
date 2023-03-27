package com.msvc.usuario.controller;

import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
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

    Integer intentos = 1;

    @GetMapping("/{id}")
    //@CircuitBreaker(name="ratingHotelFallback", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable String id){
        log.info("Cantidad de intentos: {}", intentos);
        intentos++;
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
