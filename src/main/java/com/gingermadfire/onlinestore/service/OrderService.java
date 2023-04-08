package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.OrderRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderResponseDto;
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

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDto findById(Long id) {
        return orderRepository.findById(id).map(orderMapper::map)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ по id: %d не найден", id)));
    }

    public List<OrderResponseDto> findAll() {
        List<Order> orderList = orderRepository.findAll();

        if (orderList.isEmpty()) {
            throw new NotFoundException("Ни один товар не найден");
        }

        return orderMapper.map(orderList);
    }

    public OrderResponseDto save(OrderRequestDto request) {
        Order order = orderRepository.save(orderMapper.map(request));
        return orderMapper.map(order);
    }

    public Order save(Order order) {
        orderRepository.save(order);
        return order;
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public void update(Long id, OrderRequestDto request) {
        orderRepository.save(orderMapper.map(id, request));
    }

}
