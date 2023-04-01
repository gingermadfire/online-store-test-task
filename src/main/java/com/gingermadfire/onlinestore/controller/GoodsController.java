package com.gingermadfire.onlinestore.controller;

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
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/id")
    public Goods findById(@PathVariable Long id) {
        return goodsService.findById(id);
    }

    @GetMapping
    public List<Goods> findAll() {
        return goodsService.findAll(); //TODO:return new ResponseEntity<> (goodsService.findAll(),HttpStatus.OK) не надо ли так?
    }

    @PostMapping
    public ResponseEntity<?> save(Goods goods) {
        goodsService.save(goods);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id) {
        goodsService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Goods goods) {
        goodsService.update(id, goods);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
