package com.gingermadfire.onlinestore.service;

import com.gingermadfire.onlinestore.exchange.request.GoodsRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
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

import java.math.BigDecimal;
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

        final GoodsResponse actual = goodsService.findById(1L);

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

        final List<GoodsResponse> actual = goodsService.findAll();

        Assertions.assertNotNull(actual);
        Mockito.verify(goodsRepository).findAll();
        Mockito.verify(goodsMapper).map(goods);
    }

    @Test
    void findAllShouldReturnEmptyList() {
        final List<Goods> emptyGoodsList = Collections.emptyList();

        Mockito.when(goodsRepository.findAll()).thenReturn(emptyGoodsList);

        final List<GoodsResponse> actual = goodsService.findAll();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(Collections.emptyList(), actual);
        Mockito.verify(goodsRepository).findAll();
    }

    @Test
    void saveShouldCallRepository() {
        final Goods goods = new Goods();

        Mockito.when(goodsRepository.save(Mockito.any(Goods.class)))
                .thenReturn(new Goods(1L, "pen", BigDecimal.TEN));

        GoodsRequest request = this.map(goods);
        final GoodsResponse actual = goodsService.save(request);

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
        GoodsRequest dto = new GoodsRequest();

        goodsService.update(1L, dto);

        Mockito.verify(goodsMapper).map(1L, dto);
    }

    private GoodsRequest map(Goods goods) {
        GoodsRequest dto = new GoodsRequest();

        dto.setName(goods.getName());
        dto.setPrice(goods.getPrice());

        return dto;
    }
}