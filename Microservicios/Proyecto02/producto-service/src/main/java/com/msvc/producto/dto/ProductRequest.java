package com.msvc.producto.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        String nombre,
        String descripcion,
        BigDecimal precio
) {
}
