package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.OrderLineMapper;
import com.gingermadfire.onlinestore.mapper.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.repository.OrderLineRepository;
import com.gingermadfire.onlinestore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderMapper orderMapper;
    private final OrderLineMapper orderLineMapper;

    private final OrderService orderService;
    private final GoodsService goodsService;

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    public OrderLineResponse findById(Long id) {
        return orderLineRepository.findById(id)
                .map(orderLineMapper::map)
                .orElseThrow(() -> new NotFoundException(String.format("Строка заказа по id: %d не найдена", id)));
    }

    public OrderLine get(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Строка заказа по id: %d не найдена", id)));
    }

    public List<OrderLineResponse> findAll() {
        return orderLineMapper.map(orderLineRepository.findAll());
    }

    public OrderLineResponse save(OrderLineSaveRequest request) {
        Order order = orderService.save(orderMapper.map(request));

        return orderLineMapper.map(orderLineRepository.save(orderLineMapper.map(request, order)));
    }

    public void delete(Long id) {
        orderLineRepository.deleteById(id);
    }

    public void update(Long id, OrderLineUpdateRequest request) {
        Goods goods = goodsService.get(request.getGoodsId());
        Order order = orderService.get(request.getOrder().getId());
        OrderLine orderLine = this.get(id);

        orderLine.setCount(request.getCount());
        orderLine.setGoods(goods);

        order.setDate(Instant.now());
        order.setAddress(request.getOrder().getAddress());
        order.setClient(request.getOrder().getClient());

        orderLine.setOrder(order);
        orderRepository.save(order);
        orderLineRepository.save(orderLine);
    }

}
