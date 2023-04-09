package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.persistence.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

class OrderMapperTest {

    private final OrderMapper orderMapper = new OrderMapper();

    @Test
    void shouldMapOrderRequestToOrder() {
        OrderRequest request = new OrderRequest(1L, "Client","Moscow");

        Order result = orderMapper.map(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(request.getClient(), request.getClient());
        Assertions.assertEquals(request.getAddress(), request.getAddress());
    }

    @Test
    void shouldMapOrderLineSaveRequestToOrder() {
        OrderLineSaveRequest request = new OrderLineSaveRequest(
                1L,
                new Order(1L, "Client", Instant.now(), "Moscow"),
                1
        );

        Order result = orderMapper.map(request);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(request.getOrder().getDate(), result.getDate());
        Assertions.assertEquals(request.getOrder().getClient(), result.getClient());
        Assertions.assertEquals(request.getOrder().getAddress(), result.getAddress());
    }

    @Test
    void shouldMapOrderToOrderResponse() {
        Order order = new Order(1L, "Client", Instant.now(), "Moscow");

        OrderResponse result = orderMapper.map(order);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(order.getId(), result.getId());
        Assertions.assertEquals(order.getClient(), result.getClient());
        Assertions.assertEquals(order.getDate(), result.getDate());
        Assertions.assertEquals(order.getAddress(), result.getAddress());
    }

    @Test
    void shouldMapOrderLineUpdateRequestToOrderRequest() {
        OrderLineUpdateRequest request = new OrderLineUpdateRequest(
                new OrderRequest(1L,"Client", "Moscow"),
                1L,
                1
        );

        OrderRequest result = orderMapper.map(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(request.getOrder().getClient(), result.getClient());
        Assertions.assertEquals(request.getOrder().getAddress(), result.getAddress());
    }

    @Test
    void shouldMapOrderListToOrderResponseList() {
        List<Order> orderList = List.of(new Order(1L, "Client", Instant.now(), "Moscow"));
        List<OrderResponse> resultList = orderMapper.map(orderList);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(orderList.get(0).getId(), resultList.get(0).getId());
        Assertions.assertEquals(orderList.get(0).getClient(), resultList.get(0).getClient());
        Assertions.assertEquals(orderList.get(0).getDate(), resultList.get(0).getDate());
        Assertions.assertEquals(orderList.get(0).getAddress(), resultList.get(0).getAddress());
    }

    @Test
    void shouldMapOrderRequestToOrderWithId() {
        Long id = 1L;
        OrderRequest request = new OrderRequest(1L, "Client", "Moscow");

        Order result = orderMapper.map(id, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(request.getClient(), result.getClient());
        Assertions.assertEquals(request.getAddress(), result.getAddress());
    }

}