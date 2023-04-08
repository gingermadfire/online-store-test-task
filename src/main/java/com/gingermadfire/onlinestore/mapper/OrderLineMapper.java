package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.dto.request.OrderLineSaveRequestDto;
import com.gingermadfire.onlinestore.dto.request.OrderLineUpdateRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderLineResponseDto;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderLineMapper {

    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    public OrderLine map(Long id, OrderLineUpdateRequestDto dto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(id);
        orderLine.setOrder(dto.getOrder());
        orderLine.setGoods(dto.getGoods());
        orderLine.setCount(dto.getCount());

        return orderLine;
    }

    public OrderLine map(OrderLineSaveRequestDto dto, Order order) {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setGoods(goodsMapper.map(goodsService.findById(dto.getGoodsId())));
        orderLine.setCount(dto.getCount());

        return orderLine;
    }

    public OrderLineResponseDto map(OrderLine orderLine) {
        OrderLineResponseDto dto = new OrderLineResponseDto();
        dto.setId(orderLine.getId());
        dto.setGoods(orderLine.getGoods());
        dto.setOrder(orderLine.getOrder());
        dto.setCount(orderLine.getCount());

        return dto;
    }

    public List<OrderLineResponseDto> map(List<OrderLine> orderLines) {
        return orderLines.stream().map(this::map).toList();
    }

}
