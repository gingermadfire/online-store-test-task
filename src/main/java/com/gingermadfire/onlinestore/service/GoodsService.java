package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.map.GoodsMapper;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsMapper mapper;
    private final GoodsRepository goodsRepository;

    public Goods findById(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Товар по id: %d не найден", id)));
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public void save(GoodsRequestDto request) {
        goodsRepository.save(mapper.map(request));
    }

    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    public void update(Long id, GoodsRequestDto request) {
        goodsRepository.save(mapper.map(id, request));
    }
}
