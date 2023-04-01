package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exception.GoodsNotFoundException;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public Goods findById(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException(String.format("Товар по id: %d не найден", id)));
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public void save(Goods goods) {
        goodsRepository.save(goods);
    }

    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    public void update(Long id, Goods goods) {
        goods.setId(id);
        goodsRepository.save(goods);
    }
}
