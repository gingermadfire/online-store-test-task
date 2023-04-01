package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.persistence.Order;
import com.gingermadfire.onlinestore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/id")
    public Order findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll(); //TODO:return new ResponseEntity<> (goodsService.findAll(),HttpStatus.OK) не надо ли так?
    }

    @PostMapping
    public ResponseEntity<?> save(Order order) {
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Order order) {
        orderService.update(id, order);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
