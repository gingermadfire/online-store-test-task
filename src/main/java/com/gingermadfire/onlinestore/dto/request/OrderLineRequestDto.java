package com.gingermadfire.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineRequestDto {

    public Long order_id;

    public Long goods_id;

    public Integer count;

}
