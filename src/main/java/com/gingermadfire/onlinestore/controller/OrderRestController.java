package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.dto.request.OrderRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderResponseDto;
import com.gingermadfire.onlinestore.persistence.Order;
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
    public OrderResponseDto findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping
    public List<OrderResponseDto> findAll() {
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderRequestDto request) {
        orderService.save(request);
        return new ResponseEntity<>(orderService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Order request) {
        orderService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
