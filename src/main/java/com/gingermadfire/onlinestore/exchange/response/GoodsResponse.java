package com.gingermadfire.onlinestore.exchange.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponse {

    private Long id;

    private String name;

    private BigDecimal price;

}
