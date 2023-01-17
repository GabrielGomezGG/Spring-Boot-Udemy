package com.example.form.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;


@Component
public class TiempoTrascurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTrascurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("Tiempo trascurrido interceptor: prehandler() entrando...");

        Long tiempoInicio = System.currentTimeMillis();

        request.setAttribute("tiempoInicio", tiempoInicio);

        Random random = new Random();
        Integer demora = random.nextInt(500);

        Thread.sleep(demora);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        Long tiempoFin = System.currentTimeMillis();
        Long tiempoInicio = (Long) request.getAttribute("tiempoDemora");
        Long tiempoTrascurrido = tiempoFin - tiempoInicio;

        if(tiempoTrascurrido != null){
            modelAndView.addObject("tiempoTrascurrido", tiempoTrascurrido);
        }

        logger.info("Tiempo trascurrido ".concat(tiempoTrascurrido.toString()).concat(" milisegundos"));
        logger.info("Tiempo trascurrido interceptor: posthandler() entrando...");

    }
}
