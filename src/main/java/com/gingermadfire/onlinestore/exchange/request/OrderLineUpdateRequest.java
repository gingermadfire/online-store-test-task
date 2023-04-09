package com.gingermadfire.onlinestore.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineUpdateRequest {

    private OrderRequest order;

    private Long goodsId;

    private Integer count;

}
