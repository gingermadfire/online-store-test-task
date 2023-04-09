package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.exchange.request.GoodsRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
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
    public GoodsResponse findById(@PathVariable Long id) {
        return goodsService.findById(id);
    }

    @GetMapping
    public List<GoodsResponse> findAll() {
        return goodsService.findAll();
    }

    @PostMapping
    public ResponseEntity<GoodsResponse> save(@Validated @RequestBody GoodsRequest request) {
        GoodsResponse save = goodsService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        goodsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody GoodsRequest request) {
        goodsService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
