package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.dto.response.GoodsResponseDto;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.mapper.GoodsMapper;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.repository.GoodsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsService goodsService;

    @Spy
    private GoodsMapper goodsMapper;

    @Test
    void findByIdShouldCallRepository() {
        final Goods goods = new Goods();
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Optional.of(goods));

        final GoodsResponseDto actual = goodsService.findById(1L);

        Assertions.assertNotNull(actual);
        Mockito.verify(goodsRepository).findById(1L);
        Mockito.verify(goodsMapper).map(goods);
    }

    @Test
    void findByIdShouldNotFoundException() {
        Mockito.when(goodsRepository.findById(0L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> goodsService.findById(0L),
                "Товар по id: 0 не найден"
        );

        Mockito.verify(goodsRepository).findById(0L);
    }

    @Test
    void findAllShouldCallRepository() {
        final List<Goods> goods = List.of(new Goods());
        Mockito.when(goodsRepository.findAll()).thenReturn(goods);

        final List<GoodsResponseDto> actual = goodsService.findAll();

        Assertions.assertNotNull(actual);
        Mockito.verify(goodsRepository).findAll();
        Mockito.verify(goodsMapper).map(goods);
    }

    @Test
    void findAllShouldProduceNotFoundException() {
        final List<Goods> emptyGoodsList = Collections.emptyList();
        Mockito.when(goodsRepository.findAll()).thenReturn(emptyGoodsList);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> goodsService.findAll(),
                "Ни один товар не найден"
        );

        Mockito.verify(goodsRepository).findAll();
        Mockito.verify(goodsMapper, Mockito.never()).map(emptyGoodsList);
    }

    @Test
    void saveShouldCallRepository() {
        final Goods goods = new Goods();

        GoodsRequestDto request = this.map(goods);
        final GoodsResponseDto actual = goodsService.save(request);

        Assertions.assertNotNull(actual);
        Mockito.verify(goodsMapper).map(request);
    }

    @Test
    void deleteShouldCallRepository() {

        goodsService.delete(1L);

        Mockito.verify(goodsRepository).deleteById(1L);
    }

    @Test
    void update_shouldCallRepository() {
        GoodsRequestDto dto = new GoodsRequestDto();
        goodsService.update(1L, dto);

        Mockito.verify(goodsMapper).map(1L, dto);
    }

    private GoodsRequestDto map(Goods goods) {
        GoodsRequestDto dto = new GoodsRequestDto();
        dto.setName(goods.getName());
        dto.setPrice(goods.getPrice());

        return dto;
    }
}