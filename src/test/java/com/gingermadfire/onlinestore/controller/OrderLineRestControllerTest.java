package com.gingermadfire.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gingermadfire.onlinestore.exchange.request.OrderLineSaveRequest;
import com.gingermadfire.onlinestore.exchange.request.OrderLineUpdateRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
import com.gingermadfire.onlinestore.exchange.response.OrderLineResponse;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.service.OrderLineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@WebMvcTest(OrderLineRestController.class)
class OrderLineRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineService orderLineService;

    @Test
    void findByIdShouldReturnDto() throws Exception {
        OrderLineResponse dto = new OrderLineResponse(
                1L,
                new OrderResponse(1L, "Vasya Pupkin", Instant.now(), "Kazan"),
                new GoodsResponse(1L, "T-shirt", BigDecimal.TEN),
                1
        );

        Mockito.when(orderLineService.findById(1L)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order-line/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goods.id").value(dto.getGoods().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goods.name").value(dto.getGoods().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goods.price").value(dto.getGoods().getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.order.id").value(dto.getOrder().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.order.client").value(dto.getOrder().getClient()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.order.address").value(dto.getOrder().getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.order.date").value(dto.getOrder().getDate().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(1));
    }

    @Test
    void findByIdShouldReturn404() throws Exception {
        Mockito.when(orderLineService.findById(0L))
                .thenThrow(new NotFoundException("Строка заказа по id: 0 не найдена"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order-line/{id}", 0L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Строка заказа по id: 0 не найдена"));
    }

    @Test
    void findAllShouldReturnDtos() throws Exception {
        List<OrderLineResponse> dtos = List.of(
                new OrderLineResponse(1L, new OrderResponse(), new GoodsResponse(), 1)
        );

        Mockito.when(orderLineService.findAll())
                .thenReturn(dtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order-line"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].order.id").value(dtos.get(0).getOrder().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].goods.id").value(dtos.get(0).getGoods().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].count").value(dtos.get(0).getCount()));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Mockito.when(orderLineService.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order-line"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void saveShouldReturnDto() throws Exception {
        OrderLineSaveRequest request = new OrderLineSaveRequest();
        OrderLineResponse response = new OrderLineResponse();

        Mockito.when(orderLineService.save(Mockito.any(OrderLineSaveRequest.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order-line")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(response.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goods").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.order").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(response.getCount()));
    }

    @Test
    void deleteShouldCallService() throws Exception {
        Mockito.doNothing().when(orderLineService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/order-line/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateShouldCallService() throws Exception {
        OrderLineUpdateRequest dto = new OrderLineUpdateRequest();

        Mockito.doNothing().when(orderLineService).update(1L, dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order-line/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}