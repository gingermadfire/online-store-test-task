package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;


    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::map)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ по id: %d не найден", id)));
    }

    public Order get(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ по id: %d не найден", id)));
    }

    public List<OrderResponse> findAll() {
        return orderMapper.map(orderRepository.findAll());
    }

    public OrderResponse save(OrderRequest request) {
        return orderMapper.map(orderRepository.save(orderMapper.map(request)));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public void update(Long id, OrderRequest request) {
        orderRepository.save(orderMapper.map(id, request));
    }

}
