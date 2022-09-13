package com.example.demo_di;

import com.example.demo_di.models.services.IService;
import com.example.demo_di.models.services.MiServicio;
import com.example.demo_di.models.services.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("MiServicioPrincipal")
    public IService registrarServicioSimple(){
        return new MiServicio();
    }

    @Bean("MiServicioComplejo")
    public IService registrarServicioComplejo(){
        return new MiServicioComplejo();
    }
}
