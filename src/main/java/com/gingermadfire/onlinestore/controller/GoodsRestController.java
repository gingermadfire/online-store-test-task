package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsRestController {

    private final GoodsService goodsService;

    @GetMapping("/{id}")
    public Goods findById(@PathVariable Long id) {
        return goodsService.findById(id);
    }

    @GetMapping
    public List<Goods> findAll() {
        return goodsService.findAll(); //TODO:return new ResponseEntity<> (goodsService.findAll(),HttpStatus.OK) не надо ли так?
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GoodsRequestDto request) {
        goodsService.save(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id) {
        goodsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GoodsRequestDto request) {
        goodsService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
