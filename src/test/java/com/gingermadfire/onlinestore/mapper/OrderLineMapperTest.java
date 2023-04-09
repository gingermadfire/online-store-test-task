package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.service.GoodsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderLineMapperTest {

    @Mock
    private GoodsMapper goodsMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private GoodsService goodsService;

    @InjectMocks
    private OrderLineMapper orderLineMapper;

    @Test
    void shouldMapOrderLineSaveRequestAndOrderToOrderLine() {
        OrderLineSaveRequest request = new OrderLineSaveRequest(
                1L,
                new Order(1L, "Client", Instant.now(), "Moscow"),
                1
        );
        Order order = new Order(1L, "Client", Instant.now(), "Moscow");

        Mockito.when(goodsService.findById(Mockito.any(Long.class)))
                .thenReturn(new GoodsResponse(
                        1L,
                        "Apple",
                        BigDecimal.TEN
                ));
        Mockito.when(goodsMapper.map(Mockito.any(GoodsResponse.class)))
                .thenReturn(new Goods(
                1L,
                "Apple",
                BigDecimal.TEN
        ));

        OrderLine result = orderLineMapper.map(request, order);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(request.getGoodsId(), result.getGoods().getId());
        Assertions.assertEquals(request.getOrder().getId(), request.getOrder().getId());
        Assertions.assertEquals(request.getOrder().getClient(), request.getOrder().getClient());
        Assertions.assertEquals(request.getOrder().getDate(), request.getOrder().getDate());
        Assertions.assertEquals(request.getOrder().getAddress(), request.getOrder().getAddress());
        Assertions.assertEquals(request.getCount(),result.getCount());
    }

    @Test
    void shouldMapOrderLineToOrderLineResponse() {
        OrderLine orderLine = new OrderLine(
                1L,
                new Order(1L, "Client", Instant.EPOCH, "Moscow"),
                new Goods(1L, "Apple", BigDecimal.TEN),
                1
        );

        Mockito.when(goodsMapper.map(Mockito.any(Goods.class)))
                .thenReturn(new GoodsResponse(
                1L,
                "Apple",
                BigDecimal.TEN
        ));
        Mockito.when(orderMapper.map(Mockito.any(Order.class)))
                .thenReturn(new OrderResponse(
                1L,
                "Client",
                Instant.EPOCH,
                "Moscow"
        ));

        OrderLineResponse result = orderLineMapper.map(orderLine);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(orderLine.getId(), result.getId());
        Assertions.assertEquals(orderLine.getGoods().getId(), result.getGoods().getId());
        Assertions.assertEquals(orderLine.getGoods().getName(), result.getGoods().getName());
        Assertions.assertEquals(orderLine.getGoods().getPrice(), result.getGoods().getPrice());
        Assertions.assertEquals(orderLine.getOrder().getId(), result.getOrder().getId());
        Assertions.assertEquals(orderLine.getOrder().getClient(), result.getOrder().getClient());
        Assertions.assertEquals(orderLine.getOrder().getDate(), result.getOrder().getDate());
        Assertions.assertEquals(orderLine.getOrder().getAddress(), result.getOrder().getAddress());
        Assertions.assertEquals(orderLine.getCount(),result.getCount());
    }

    @Test
    void shouldMapOrderLineListToOrderLineResponseList() {
        List<OrderLine> orderLineList = List.of(new OrderLine(
                1L,
                new Order(1L, "Client", Instant.EPOCH, "Moscow"),
                new Goods(1L, "Apple", BigDecimal.TEN),
                1
        ));
        List<OrderLineResponse> result = orderLineMapper.map(orderLineList);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(orderLineList.size(), result.size());
    }

}