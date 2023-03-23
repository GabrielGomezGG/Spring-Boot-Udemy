package com.example.form.editors;

import com.example.form.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PaisPropertiesEditor extends PropertyEditorSupport {

    @Autowired
    private PaisService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        try {
            this.setValue(service.obtenerPorId(Integer.parseInt(idString)));
        } catch (NumberFormatException e) {
            setValue(null);
        }

    }
}
