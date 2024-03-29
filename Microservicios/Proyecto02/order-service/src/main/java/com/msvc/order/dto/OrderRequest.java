package com.msvc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    private List<OrderLineItemDto> orderLineItems = new ArrayList<>();
}
