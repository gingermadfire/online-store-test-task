package com.gingermadfire.onlinestore.exchange.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLineResponse {

    private Long id;

    private OrderResponse order;

    private GoodsResponse goods;

    private Integer count;

}
