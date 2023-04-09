package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderMapper orderMapper;

    @Test
    void findByIdShouldCallRepository() {
        final Order order = new Order();
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        final OrderResponse actual = orderService.findById(1L);

        Assertions.assertNotNull(actual);
        Mockito.verify(orderRepository).findById(1L);
        Mockito.verify(orderMapper).map(order);
    }

    @Test
    void findByIdShouldNotFoundException() {
        Mockito.when(orderRepository.findById(0L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> orderService.findById(0L),
                "Товар по id: 0 не найден"
        );

        Mockito.verify(orderRepository).findById(0L);
    }

    @Test
    void findAllShouldCallRepository() {
        final List<Order> orderList = List.of(new Order());
        Mockito.when(orderRepository.findAll()).thenReturn(orderList);

        final List<OrderResponse> actual = orderService.findAll();

        Assertions.assertNotNull(actual);
        Mockito.verify(orderRepository).findAll();
        Mockito.verify(orderMapper).map(orderList);
    }

    @Test
    void findAllShouldReturnEmptyList() {
        final List<Order> emptyOrderList = Collections.emptyList();
        Mockito.when(orderRepository.findAll()).thenReturn(emptyOrderList);

        final List<OrderResponse> actual = orderService.findAll();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(Collections.emptyList(), actual);
        Mockito.verify(orderRepository).findAll();
    }

    @Test
    void saveShouldCallRepository() {
        final Order order = new Order();
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(new Order(1L, "a", Instant.now(), "Kazan"));

        OrderRequest request = this.map(order);
        final OrderResponse actual = orderService.save(request);

        Assertions.assertNotNull(actual);
        Mockito.verify(orderMapper).map(request);
    }

    @Test
    void deleteShouldCallRepository() {
        orderService.delete(1L);

        Mockito.verify(orderRepository).deleteById(1L);
    }

    @Test
    void updateShouldCallRepository() {
        OrderRequest dto = new OrderRequest();
        orderService.update(1L, dto);

        Mockito.verify(orderMapper).map(1L, dto);
    }

    private OrderRequest map(Order order) {
        OrderRequest dto = new OrderRequest();

        dto.setAddress(order.getAddress());
        dto.setClient(order.getClient());

        return dto;
    }
}