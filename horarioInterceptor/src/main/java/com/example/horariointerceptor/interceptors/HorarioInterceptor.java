package com.example.horariointerceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${confir.horario.apertura}")
    private Integer apertura;

    @Value("${confir.horario.cierre}")
    private Integer cierre;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if(hora >= apertura && hora < cierre){
            StringBuilder mensaje = new StringBuilder("Bienvenido al servicio al cliente.")
                    .append(", atendemos desde las").append(apertura)
                    .append("hrs, hasta las ").append(cierre)
                    .append(". Gracias por su visita.");

            request.setAttribute("mensaje", mensaje.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String mensajeHorario = (String) request.getAttribute("mensaje");
        modelAndView.addObject("horario",mensajeHorario);
    }
}
