package com.gingermadfire.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gingermadfire.onlinestore.exchange.request.GoodsRequest;
import com.gingermadfire.onlinestore.exchange.response.GoodsResponse;
import com.gingermadfire.onlinestore.exception.NotFoundException;
import com.gingermadfire.onlinestore.service.GoodsService;
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
import java.util.Collections;
import java.util.List;

@WebMvcTest(GoodsRestController.class)
class GoodsRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsService goodsService;

    @Test
    void findByIdShouldReturnResponse() throws Exception {
        GoodsResponse response = new GoodsResponse(1L, "qwerty", BigDecimal.TEN);

        Mockito.when(goodsService.findById(1L))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goods/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(response.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(response.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(response.getPrice()));
    }

    @Test
    void findByIdShouldReturn404() throws Exception {
        Mockito.when(goodsService.findById(0L))
                .thenThrow(new NotFoundException("Товар по id: 0 не найден"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goods/{id}", 0L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Товар по id: 0 не найден"));
    }

    @Test
    void findAllShouldReturnDtos() throws Exception {
        List<GoodsResponse> dtos = List.of(new GoodsResponse());

        Mockito.when(goodsService.findAll())
                .thenReturn(dtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goods"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].id").value(dtos.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].name").value(dtos.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:1].price").value(dtos.get(0).getPrice()));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Mockito.when(goodsService.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goods"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void saveShouldReturnDto() throws Exception {
        GoodsRequest request = new GoodsRequest("bbc", BigDecimal.TEN);
        GoodsResponse response = new GoodsResponse(1L, "bbc", BigDecimal.TEN);

        Mockito.when(goodsService.save(Mockito.any(GoodsRequest.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/goods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(request.getPrice()));
    }

    @Test
    void deleteShouldCallService() throws Exception {
        Mockito.doNothing().when(goodsService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/goods/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateShouldCallService() throws Exception {
        GoodsRequest dto = new GoodsRequest();

        Mockito.doNothing().when(goodsService).update(1L, dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/goods/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}