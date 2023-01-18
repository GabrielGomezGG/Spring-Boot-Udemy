package com.example.jpa.models.dao;

import com.example.jpa.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Long> {

}
