package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.dto.response.GoodsResponseDto;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.GoodsMapper;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsMapper goodsMapper;
    private final GoodsRepository goodsRepository;

    public GoodsResponseDto findById(Long id) {
        return goodsRepository.findById(id).map(goodsMapper::map)
                .orElseThrow(() -> new NotFoundException(String.format("Товар по id: %d не найден", id)));
    }

    public GoodsResponseDto findGoodsById(Long id) {
        return goodsRepository.findById(id).map(goodsMapper::map)
                .orElseThrow(() -> new NotFoundException(String.format("Товар по id: %d не найден", id)));
    }

    public List<GoodsResponseDto> findAll() {
        return goodsMapper.map(goodsRepository.findAll());
    }

    public GoodsResponseDto save(GoodsRequestDto request) {
        Goods goods = goodsMapper.map(request);
        goodsRepository.save(goods);
        return goodsMapper.map(goods);
    }

    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    public void update(Long id, GoodsRequestDto request) {
        goodsRepository.save(goodsMapper.map(id, request));
    }
}
