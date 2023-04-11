package com.msvc.order.mapper;

import com.msvc.order.dto.OrderLineItemDto;
import com.msvc.order.dto.OrderRequest;
import com.msvc.order.entity.Order;
import com.msvc.order.entity.OrderLineItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order dtoToOrder(OrderRequest orderRequest);
    OrderRequest orderToDto(Order order);

    OrderLineItemDto orderLineItemToDto(OrderLineItem orderLineItem);
    OrderLineItem dtoToOrderLineItem(OrderLineItemDto orderLineItemDto);
}
