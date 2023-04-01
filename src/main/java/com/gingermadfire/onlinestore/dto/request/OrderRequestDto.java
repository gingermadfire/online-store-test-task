package com.gingermadfire.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderRequestDto {

    private String client;

    private Instant date;

    private String address;
}
