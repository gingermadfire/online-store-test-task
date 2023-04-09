package com.gingermadfire.onlinestore.exchange.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineRequest {

    public Long orderId;

    public Long goodsId;

    public Integer count;

}
