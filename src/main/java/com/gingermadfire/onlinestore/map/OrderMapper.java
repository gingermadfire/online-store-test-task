package com.gingermadfire.onlinestore.map;

import com.gingermadfire.onlinestore.dto.request.OrderRequestDto;
import com.gingermadfire.onlinestore.persistence.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order map(OrderRequestDto dto) {
        Order order = new Order();
        order.setClient(dto.getClient());
        order.setDate(dto.getDate()); //TODO:дата чего?
        order.setAddress(dto.getAddress());

        return order;
    }

    public Order map(Long id, OrderRequestDto dto) {
        Order order = new Order();
        order.setId(id);
        order.setClient(dto.getClient());
        order.setDate(dto.getDate());
        order.setAddress(dto.getAddress());

        return order;
    }
}
