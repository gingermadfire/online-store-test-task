package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.dto.request.GoodsRequestDto;
import com.gingermadfire.onlinestore.dto.response.GoodsResponseDto;
import com.gingermadfire.onlinestore.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/goods")
public class GoodsRestController {

    private final GoodsService goodsService;

    @GetMapping("/{id}")
    public GoodsResponseDto findById(@PathVariable Long id) {
        return goodsService.findById(id);
    }

    @GetMapping
    public List<GoodsResponseDto> findAll() {
        return goodsService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody GoodsRequestDto request) {
        return new ResponseEntity<>(goodsService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        goodsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GoodsRequestDto request) {
        goodsService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
