package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
import com.gingermadfire.onlinestore.mapper.OrderLineMapper;
import com.gingermadfire.onlinestore.mapper.OrderMapper;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.repository.OrderLineRepository;
import com.gingermadfire.onlinestore.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {

    @Mock
    private OrderLineRepository orderLineRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderLineMapper orderLineMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private GoodsService goodsService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderLineService orderLineService;

    @Test
    void findByIdShouldCallRepository() {
        final OrderLine orderLine = new OrderLine();
        Mockito.when(orderLineRepository.findById(1L)).thenReturn(Optional.of(orderLine));
        Mockito.when(orderLineMapper.map(orderLine)).thenReturn(new OrderLineResponse());

        final OrderLineResponse actual = orderLineService.findById(1L);

        Assertions.assertNotNull(actual);
        Mockito.verify(orderLineRepository).findById(1L);
        Mockito.verify(orderLineMapper).map(orderLine);
    }

    @Test
    void findByIdShouldNotFoundException() {
        Mockito.when(orderLineRepository.findById(0L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> orderLineService.findById(0L),
                "Товар по id: 0 не найден"
        );

        Mockito.verify(orderLineRepository).findById(0L);
    }

    @Test
    void findAllShouldCallRepository() {
        final List<OrderLine> orderLineList = List.of(new OrderLine());
        Mockito.when(orderLineRepository.findAll()).thenReturn(orderLineList);

        final List<OrderLineResponse> actual = orderLineService.findAll();

        Assertions.assertNotNull(actual);
        Mockito.verify(orderLineRepository).findAll();
        Mockito.verify(orderLineMapper).map(orderLineList);
    }

    @Test
    void findAllShouldReturnEmptyList() {
        final List<OrderLine> emptyOrderLineList = Collections.emptyList();
        Mockito.when(orderLineRepository.findAll()).thenReturn(emptyOrderLineList);

        final List<OrderLineResponse> actual = orderLineService.findAll();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(Collections.emptyList(), actual);
        Mockito.verify(orderLineRepository).findAll();
    }

    @Test
    void saveShouldCallRepository() {
        final OrderLine orderLine = new OrderLine(1L,
                new Order(1L, "a", Instant.EPOCH, "Kazan"),
                new Goods(1L, "a", BigDecimal.TEN),
                1);
        Mockito.when(orderMapper.map(Mockito.any(OrderLineSaveRequest.class))).thenReturn(new Order());
        Mockito.when(orderService.save(Mockito.any(Order.class))).thenReturn(new Order());
        Mockito.when(orderLineMapper.map(Mockito.any(OrderLineSaveRequest.class), Mockito.any(Order.class)))
                        .thenReturn(new OrderLine());
        Mockito.when(orderLineRepository.save(Mockito.any(OrderLine.class))).thenReturn(new OrderLine());


        OrderLineSaveRequest request = this.map(orderLine);
        orderLineService.save(request);

        Mockito.verify(orderLineRepository).save(Mockito.any(OrderLine.class));
    }

    @Test
    void deleteShouldCallRepository() {
        orderLineService.delete(1L);

        Mockito.verify(orderLineRepository).deleteById(1L);
    }

    @Test
    void updateShouldCallRepository() {
        OrderLineUpdateRequest request = new OrderLineUpdateRequest(
                new OrderRequest(1L,"Client", "Moscow" ),
                1L,
                1
        );
        Order order = new Order();

        Mockito.when(goodsService.get(1L)).thenReturn(new Goods());
        Mockito.when(orderService.get(1L)).thenReturn(order);
        Mockito.when(orderLineRepository.findById(1L)).thenReturn(Optional.of(new OrderLine(
                1L,
                new Order(1L, "Client", Instant.now(), "Moscow"),
                new Goods(1L, "Apple", BigDecimal.TEN),
                1
        )));
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        orderLineService.update(1L, request);

        Mockito.verify(orderLineRepository).save(Mockito.any(OrderLine.class));
    }

    private OrderLineSaveRequest map(OrderLine orderLine) {
        OrderLineSaveRequest dto = new OrderLineSaveRequest();

        dto.setGoodsId(orderLine.getGoods().getId());
        dto.setOrder(orderLine.getOrder());
        dto.setCount(orderLine.getCount());

        return dto;
    }

}