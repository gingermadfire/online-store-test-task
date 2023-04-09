package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.GoodsRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
import com.gingermadfire.onlinestore.persistence.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class GoodsMapperTest {

    private final GoodsMapper goodsMapper = new GoodsMapper();

    @Test
    void shouldMapGoodsRequestToGoods() {
        GoodsRequest request = new GoodsRequest("Apple", BigDecimal.TEN);
        Goods result = goodsMapper.map(request);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(request.getName(), result.getName());
        Assertions.assertEquals(request.getPrice(), result.getPrice());
    }

    @Test
    void shouldMapGoodsRequestToGoodsWithId() {
        Long id = 1L;
        GoodsRequest request = new GoodsRequest("Apple", BigDecimal.TEN);
        Goods result = goodsMapper.map(id, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals(request.getName(), result.getName());
        Assertions.assertEquals(request.getPrice(), result.getPrice());
    }

    @Test
    void shouldMapGoodsToGoodsResponse() {
        Goods goods = new Goods(1L, "Apple", BigDecimal.TEN);
        GoodsResponse result = goodsMapper.map(goods);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(goods.getId(), result.getId());
        Assertions.assertEquals(goods.getName(), result.getName());
        Assertions.assertEquals(goods.getPrice(), result.getPrice());
    }

    @Test
    void shouldMapGoodsListToGoodsResponseList() {
        List<Goods> goodsList = List.of(new Goods(1L, "Apple", BigDecimal.TEN));
        List<GoodsResponse> result = goodsMapper.map(goodsList);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(goodsList.size(), result.size());
    }

    @Test
    void shouldMapGoodsResponseToGoods() {
        GoodsResponse response = new GoodsResponse(1L, "Apple", BigDecimal.TEN);
        Goods result = goodsMapper.map(response);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.getId(), result.getId());
        Assertions.assertEquals(response.getName(), result.getName());
        Assertions.assertEquals(response.getPrice(), result.getPrice());
    }

}