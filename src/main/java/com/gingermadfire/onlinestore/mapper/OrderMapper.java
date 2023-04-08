package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.dto.request.OrderLineSaveRequestDto;
import com.gingermadfire.onlinestore.dto.request.OrderLineUpdateRequestDto;
import com.gingermadfire.onlinestore.dto.request.OrderRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderResponseDto;
import com.gingermadfire.onlinestore.persistence.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class OrderMapper {

    public Order map(OrderRequestDto dto) {
        Order order = new Order();
        order.setClient(dto.getClient());
        order.setDate(Instant.now());
        order.setAddress(dto.getAddress());

        return order;
    }

    public Order map(OrderLineSaveRequestDto dto) {
        Order order = new Order();
        order.setClient(dto.getClient());
        order.setDate(Instant.now());
        order.setAddress(dto.getAddress());

        return order;
    }

    public OrderResponseDto map(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setAddress(order.getAddress());
        dto.setDate(Instant.now());
        dto.setClient(order.getClient());

        return dto;
    }

    public OrderRequestDto map(OrderLineUpdateRequestDto dto) {
        OrderRequestDto order = new OrderRequestDto();
        order.setClient(dto.getOrder().getClient());
        order.setAddress(dto.getOrder().getAddress());

        return order;
    }

    public List<OrderResponseDto> map(List<Order> orderList) {
        return orderList.stream().map(this::map).toList();
    }

    public Order map(Long id, OrderRequestDto dto) {
        Order order = new Order();
        order.setId(id);
        order.setDate(Instant.now());
        order.setClient(dto.getClient());
        order.setAddress(dto.getAddress());

        return order;
    }
}
