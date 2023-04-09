package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderLineMapper {

    private final GoodsMapper goodsMapper;
    private final OrderMapper orderMapper;

    private final GoodsService goodsService;

    public OrderLine map(OrderLineSaveRequest request, Order order) {
        OrderLine orderLine = new OrderLine();

        orderLine.setOrder(order);
        orderLine.setGoods(goodsMapper.map(goodsService.findById(request.getGoodsId())));
        orderLine.setCount(request.getCount());

        return orderLine;
    }

    public OrderLineResponse map(OrderLine orderLine) {
        OrderLineResponse response = new OrderLineResponse();

        response.setId(orderLine.getId());
        response.setGoods(goodsMapper.map(orderLine.getGoods()));
        response.setOrder(orderMapper.map(orderLine.getOrder()));
        response.setCount(orderLine.getCount());

        return response;
    }

    public List<OrderLineResponse> map(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(this::map)
                .toList();
    }

}
