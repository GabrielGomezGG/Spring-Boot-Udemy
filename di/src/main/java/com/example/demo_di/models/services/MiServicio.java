package com.example.demo_di.models.services;

import org.springframework.stereotype.Service;

//@Service("MiServicioPrincipal")
public class MiServicio implements IService {
    @Override
    public String algo() {
        return "aaaaaaaaaaaaaaaaa";
    }
}
