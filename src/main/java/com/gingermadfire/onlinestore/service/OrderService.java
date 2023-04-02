package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.OrderRequestDto;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.map.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ по id: %d не найден", id)));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(OrderRequestDto request) {
        orderRepository.save(mapper.map(request));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public void update(Long id, OrderRequestDto request) {
        orderRepository.save(mapper.map(id, request));
    }

}
