package com.gingermadfire.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineRequestDto {

    public Long orderId;

    public Long goodsId;

    public Integer count;

}
