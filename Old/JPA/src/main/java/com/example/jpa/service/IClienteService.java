package com.example.jpa.service;

import com.example.jpa.models.entity.Cliente;
import com.example.jpa.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);

    public void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);

    public List<Producto> findByNombre(String term);

}
