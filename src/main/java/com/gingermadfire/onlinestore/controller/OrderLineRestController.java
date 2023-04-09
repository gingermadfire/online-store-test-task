package com.gingermadfire.onlinestore.controller;

import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
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
    public OrderLineResponse findById(@PathVariable Long id) {
        return orderLineService.findById(id);
    }

    @GetMapping
    public List<OrderLineResponse> findAll() {
        return orderLineService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderLineSaveRequest request) {
        return new ResponseEntity<>(orderLineService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderLineService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody OrderLineUpdateRequest request) {
        orderLineService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
