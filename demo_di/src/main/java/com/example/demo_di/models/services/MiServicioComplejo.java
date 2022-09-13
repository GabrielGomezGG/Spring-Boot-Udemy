package com.example.demo_di.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MiServicioComplejo implements IService {
    @Override
    public String algo() {
        return "toy complejo";
    }
}
