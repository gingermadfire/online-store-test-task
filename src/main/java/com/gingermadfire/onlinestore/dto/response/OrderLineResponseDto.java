package com.gingermadfire.onlinestore.dto.response;

import com.gingermadfire.onlinestore.persistence.Goods;
import com.gingermadfire.onlinestore.persistence.Order;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderLineResponseDto {

    private Long id;

    private Order order;

    private Goods goods;

    private Integer count;

}
