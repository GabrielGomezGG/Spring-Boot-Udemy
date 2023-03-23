package com.example.form.editors;

import com.example.form.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RolesEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            this.setValue(roleService.getRolesPorId(Integer.parseInt(idString)));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
