package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.persistence.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class OrderMapper {

    public Order map(OrderRequest request) {
        Order order = new Order();

        order.setClient(request.getClient());
        order.setDate(Instant.now());
        order.setAddress(request.getAddress());

        return order;
    }

    public Order map(OrderLineSaveRequest request) {
        Order order = new Order();

        order.setClient(request.getOrder().getClient());
        order.setDate(Instant.now());
        order.setAddress(request.getOrder().getAddress());

        return order;
    }

    public OrderResponse map(Order order) {
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setAddress(order.getAddress());
        response.setDate(order.getDate());
        response.setClient(order.getClient());

        return response;
    }

    public OrderRequest map(OrderLineUpdateRequest request) {
        OrderRequest order = new OrderRequest();

        order.setClient(request.getOrder().getClient());
        order.setAddress(request.getOrder().getAddress());

        return order;
    }

    public List<OrderResponse> map(List<Order> orderList) {
        return orderList.stream()
                .map(this::map)
                .toList();
    }

    public Order map(Long id, OrderRequest request) {
        Order order = new Order();

        order.setId(id);
        order.setDate(Instant.now());
        order.setClient(request.getClient());
        order.setAddress(request.getAddress());

        return order;
    }
}
