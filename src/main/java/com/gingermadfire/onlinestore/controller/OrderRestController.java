package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest request) {
        return new ResponseEntity<>(orderService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody OrderRequest request) {
        orderService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
