package com.gingermadfire.onlinestore.map;

import com.gingermadfire.onlinestore.dto.request.OrderLineRequestDto;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine map(OrderLineRequestDto dto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrderId(dto.getOrderId());
        orderLine.setGoodsId(dto.getGoodsId());
        orderLine.setCount(dto.getCount());

        return orderLine;
    }

    public OrderLine map(Long id, OrderLineRequestDto dto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(id);
        orderLine.setOrderId(dto.getOrderId());
        orderLine.setGoodsId(dto.getGoodsId());
        orderLine.setCount(dto.getCount());

        return orderLine;
    }

}
