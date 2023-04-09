package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.exchange.request.GoodsRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
import com.gingermadfire.onlinestore.persistence.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsMapper {

    public Goods map(GoodsRequest dto) {
        Goods goods = new Goods();

        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }

    public Goods map(Long id, GoodsRequest dto) {
        Goods goods = new Goods();

        goods.setId(id);
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }

    public GoodsResponse map(Goods goods) {
        GoodsResponse dto = new GoodsResponse();

        dto.setId(goods.getId());
        dto.setName(goods.getName());
        dto.setPrice(goods.getPrice());

        return dto;
    }

    public List<GoodsResponse> map(List<Goods> goodsList) {
        return goodsList.stream()
                .map(this::map)
                .toList();
    }

    public Goods map(GoodsResponse dto) {
        Goods goods = new Goods();

        goods.setId(dto.getId());
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }
}
