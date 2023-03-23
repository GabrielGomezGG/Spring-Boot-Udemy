package com.example.form.services;

import com.example.form.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements PaisService{

    private List<Pais> lista;
    public PaisServiceImpl(){
        lista = Arrays.asList(
                new Pais(1, "AR", "Argentina"),
                new Pais(2,"CH","Chile"),
                new Pais(3, "MX","Mexico"),
                new Pais(4, "VN","Venezuela"),
                new Pais(5, "PY","Paraguay"));
    }
    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for(Pais pais : lista){
            if(pais.getId() == id){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
