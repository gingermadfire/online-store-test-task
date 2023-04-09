package com.gingermadfire.onlinestore.exchange.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsRequest {

    private String name;

    private BigDecimal price;
}
