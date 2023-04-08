package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.OrderLineSaveRequestDto;
import com.gingermadfire.onlinestore.dto.request.OrderLineUpdateRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderLineResponseDto;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.OrderLineMapper;
import com.gingermadfire.onlinestore.mapper.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderLineResponseDto findById(Long id) {
        return orderLineRepository
                .findById(id)
                .map(orderLineMapper::map)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Строка заказа по id: %d не найдена", id))
                );
    }

    public List<OrderLineResponseDto> findAll() {
        return orderLineMapper.map(orderLineRepository.findAll());
    }

    public OrderLineResponseDto save(OrderLineSaveRequestDto request) {
        Order order = orderService.save(orderMapper.map(request));

        return orderLineMapper.map(orderLineRepository.save( orderLineMapper.map(request, order)));
    }

    public void delete(Long id) {
        orderLineRepository.deleteById(id);
    }

    public void update(Long id, OrderLineUpdateRequestDto request) {
        this.orderService.update(request.getOrder().getId(), orderMapper.map(request));
        orderLineRepository.save(orderLineMapper.map(id, request));
    }

}
