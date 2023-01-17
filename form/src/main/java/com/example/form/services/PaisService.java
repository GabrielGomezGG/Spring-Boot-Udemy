package com.example.form.services;

import com.example.form.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PaisService {

    public List<Pais>listar();
    public Pais obtenerPorId(Integer id);
}
