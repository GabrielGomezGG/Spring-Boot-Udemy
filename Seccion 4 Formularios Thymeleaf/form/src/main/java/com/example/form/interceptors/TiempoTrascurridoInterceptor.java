package com.example.form.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;


@Component("tiempoTrascurridoInterceptor")
public class TiempoTrascurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTrascurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof  HandlerMethod){
            HandlerMethod metodo = (HandlerMethod) handler;
            logger.info("Es un metodo del controlador: ".concat(metodo.getMethod().getName()));
        }

        logger.info("Tiempo trascurrido interceptor: prehandler() entrando...");

        long tiempoInicio = System.currentTimeMillis();

        request.setAttribute("tiempoInicio", tiempoInicio);

        Random random = new Random();
        Integer demora = random.nextInt(500);
        Thread.sleep(demora);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        long tiempoFin = System.currentTimeMillis();
        long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        long tiempoTrascurrido = tiempoFin - tiempoInicio;

        if(handler instanceof  HandlerMethod && modelAndView != null){
            modelAndView.addObject("tiempoTrascurrido", tiempoTrascurrido);
        }

        logger.info("Tiempo trascurrido " + tiempoTrascurrido + " milisegundos");
        logger.info("Tiempo trascurrido interceptor: posthandler() entrando...");

    }
}
