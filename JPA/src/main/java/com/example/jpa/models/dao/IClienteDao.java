package com.example.jpa.models.dao;

import com.example.jpa.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    List<Cliente> findAll();

    void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);
}
