package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.dto.request.OrderLineSaveRequestDto;
import com.gingermadfire.onlinestore.dto.request.OrderLineUpdateRequestDto;
import com.gingermadfire.onlinestore.dto.response.OrderLineResponseDto;
import com.gingermadfire.onlinestore.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineRestController {

    private final OrderLineService orderLineService;

    @GetMapping("/{id}")
    public OrderLineResponseDto findById(@PathVariable Long id) {
        return orderLineService.findById(id);
    }

    @GetMapping
    public List<OrderLineResponseDto> findAll() {
        return orderLineService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderLineSaveRequestDto request) {
        return new ResponseEntity<>(orderLineService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderLineService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderLineUpdateRequestDto request) {
        orderLineService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
