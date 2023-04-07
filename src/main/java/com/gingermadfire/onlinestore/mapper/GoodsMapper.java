package com.gingermadfire.onlinestore.mapper;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.dto.response.GoodsResponseDto;
import com.gingermadfire.onlinestore.persistence.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsMapper {

    public Goods map(GoodsRequestDto dto) {
        Goods goods = new Goods();
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }

    public Goods map(Long id, GoodsRequestDto dto) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }

    public GoodsResponseDto map(Goods goods) {
        GoodsResponseDto dto = new GoodsResponseDto();
        dto.setId(goods.getId());
        dto.setName(goods.getName());
        dto.setPrice(goods.getPrice());

        return dto;
    }

    public List<GoodsResponseDto> map(List<Goods> goodsList) {
        return goodsList.stream().map(this::map).toList();
    }

    public Goods map(GoodsResponseDto dto) {
        Goods goods = new Goods();
        goods.setId(dto.getId());
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());

        return goods;
    }
}
