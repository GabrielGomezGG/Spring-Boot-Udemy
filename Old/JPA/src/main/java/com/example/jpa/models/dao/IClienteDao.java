package com.example.jpa.models.dao;

import com.example.jpa.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<Cliente,Long> {

}
