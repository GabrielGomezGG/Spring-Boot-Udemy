package com.example.jpa.models.dao;

import com.example.jpa.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    public List<Cliente> findAll();
}
