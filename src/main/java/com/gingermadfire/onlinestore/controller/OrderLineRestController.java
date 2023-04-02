package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.dto.request.OrderLineRequestDto;
import com.gingermadfire.onlinestore.persistence.OrderLine;
import com.gingermadfire.onlinestore.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-line")
@RequiredArgsConstructor
public class OrderLineRestController {

    private final OrderLineService orderLineService;

    @GetMapping("/{id}")
    public OrderLine findById(@PathVariable Long id) {
        return orderLineService.findById(id);
    }

    @GetMapping
    public List<OrderLine> findAll() {
        return orderLineService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderLineRequestDto request) {
        orderLineService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderLineService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderLineRequestDto request) {
        orderLineService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
