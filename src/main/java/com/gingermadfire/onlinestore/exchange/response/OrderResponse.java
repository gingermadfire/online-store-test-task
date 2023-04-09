package com.gingermadfire.onlinestore.exchange.response;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private Long id;

    private String client;

    private Instant date;

    private String address;

}
