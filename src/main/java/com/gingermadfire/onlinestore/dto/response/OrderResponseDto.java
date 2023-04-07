package com.gingermadfire.onlinestore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class OrderResponseDto {

    private Long id;

    private String client;

    private Instant date;

    private String address;

}
