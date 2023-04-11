package com.msvc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLineItemDto {

    private Long id;
    private String codeSku;
    private BigDecimal price;
    private Integer count;
}
