package com.example.jpa.models.dao;

import com.example.jpa.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteDaoImpl implements IClienteDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> getAll() {
        return entityManager.createQuery("from clientes").getResultList();
    }
}
