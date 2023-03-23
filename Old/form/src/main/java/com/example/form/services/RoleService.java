package com.example.form.services;

import com.example.form.models.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getRoles();
    public Role getRolesPorId(Integer id);
}
