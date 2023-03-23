package com.example.form.services;

import com.example.form.models.domain.Pais;
import com.example.form.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class RoleServiceImpl implements RoleService{

    private List<Role> roles;
    public RoleServiceImpl(){
        roles = Arrays.asList(
                new Role(1,"Administrador", "ROLE_ADMIN"),
                new Role(2,"Usuario", "ROLE_USER"),
                new Role(3,"Moderador", "ROLE_MODERATOR")
        );
    }
    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public Role getRolesPorId(Integer id) {
        Role resultado = null;
        for(Role role : roles){
            if(role.getId() == id){
                resultado = role;
                break;
            }
        }
        return resultado;
    }
}
