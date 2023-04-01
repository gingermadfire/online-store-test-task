package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.OrderLineRequestDto;
import com.gingermadfire.onlinestore.exception.OrderLineNotFoundException;
import com.gingermadfire.onlinestore.map.OrderLineMapper;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public OrderLine findById(Long id) {
        return orderLineRepository
                .findById(id)
                .orElseThrow(
                        () -> new OrderLineNotFoundException(String.format("Строка заказа по id: %d не найдена", id))
                );
    }

    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    public void save(OrderLineRequestDto request) {
        orderLineRepository.save(mapper.map(request));
    }

    public void delete(Long id) {
        orderLineRepository.deleteById(id);
    }

    public void update(Long id, OrderLineRequestDto request) {
        orderLineRepository.save(mapper.map(id, request));
    }

}
