package com.msvc.order.service;

import com.msvc.order.dto.OrderRequest;
import com.msvc.order.entity.Order;
import com.msvc.order.entity.OrderLineItem;
import com.msvc.order.mapper.OrderMapper;
import com.msvc.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        var order = new Order();
        order.setNumberOrder(UUID.randomUUID().toString());

        //var orderListItem = orderMapper.dtoToOrderLineItem(orderRequest.getOrderLineItems());
        var orderListItem = orderRequest.getOrderLineItems()
                .stream()
                .map(it -> orderMapper.dtoToOrderLineItem(it))
                .toList();
        order.setOrderLineItems(orderListItem);
        return orderRepository.save(order);
    }
}
