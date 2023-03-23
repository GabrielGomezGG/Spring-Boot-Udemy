package com.example.errores.services;

import com.example.errores.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private List<Usuario> listaUsuarios;

    public UsuarioServiceImpl(){
        listaUsuarios = Arrays.asList(
                new Usuario(1,"Titi", "Toto"),
                new Usuario(2,"Popo", "Lolo"),
                new Usuario(3,"Tutu", "Lulu"),
                new Usuario(4,"Bobo", "Roro")
        );
    }

    @Override
    public List<Usuario> getAllUsuario() {
        return listaUsuarios;
    }

    @Override
    public Usuario getUsuarioForId(Integer id) {
        Usuario resultado = null;

        for(Usuario us : listaUsuarios){
            if (us.getId().equals(id)){
                resultado = us;
                break;
            }
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> getUsuarioForIdOpcional(Integer id) {
        Usuario resultado = getUsuarioForId(id);
        return Optional.ofNullable(resultado);
    }
}
