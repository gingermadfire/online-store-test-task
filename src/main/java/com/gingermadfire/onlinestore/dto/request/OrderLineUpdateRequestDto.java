package com.gingermadfire.onlinestore.dto.request;

import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.persistence.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineUpdateRequestDto {

    public Order order;

    public Goods goods;

    public Integer count;

}
