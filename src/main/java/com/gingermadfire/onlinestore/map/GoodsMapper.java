package com.gingermadfire.onlinestore.map;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.persistence.Goods;
import org.springframework.stereotype.Component;

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
}
