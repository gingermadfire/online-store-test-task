package com.gingermadfire.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gingermadfire.onlinestore.exchange.request.OrderRequest;
import com.gingermadfire.onlinestore.exchange.response.OrderResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void findByIdShouldReturnDto() throws Exception {
        OrderResponse dto = new OrderResponse(1L, "a", Instant.EPOCH, "Kazan");

        Mockito.when(orderService.findById(1L))
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.client").value(dto.getClient()))
                .andExpect(jsonPath("$.date").value(dto.getDate().toString()))
                .andExpect(jsonPath("$.address").value(dto.getAddress()));
    }

    @Test
    void findByIdShouldReturn404() throws Exception {
        Mockito.when(orderService.findById(0L))
                .thenThrow(new NotFoundException("Товар по id: 0 не найден"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{id}", 0L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("Товар по id: 0 не найден"));
    }

    @Test
    void findAllShouldReturnDtos() throws Exception {
        List<OrderResponse> dtos = List.of(new OrderResponse(1L, "a", Instant.EPOCH, "Kazan"));

        Mockito.when(orderService.findAll())
                .thenReturn(dtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[:1].client").value(dtos.get(0).getClient()))
                .andExpect(jsonPath("$.[:1].date").value(dtos.get(0).getDate().toString()))
                .andExpect(jsonPath("$.[:1].address").value(dtos.get(0).getAddress()));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Mockito.when(orderService.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void saveShouldReturnDto() throws Exception {
        OrderRequest request = new OrderRequest(1L,"a", "Kazan");
        OrderResponse response = new OrderResponse(1L, "a", Instant.EPOCH, "Kazan");

        Mockito.when(orderService.save(Mockito.any(OrderRequest.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.client").value(request.getClient()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(request.getAddress()));
    }

    @Test
    void deleteShouldCallService() throws Exception {
        Mockito.doNothing().when(orderService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/order/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateShouldCallService() throws Exception {
        OrderRequest dto = new OrderRequest();

        Mockito.doNothing().when(orderService).update(1L, dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}