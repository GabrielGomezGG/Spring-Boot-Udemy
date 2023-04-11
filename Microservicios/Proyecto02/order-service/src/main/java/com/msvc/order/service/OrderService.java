package com.msvc.order.service;

import com.msvc.order.dto.OrderRequest;
import com.msvc.order.entity.Order;

public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);
}
