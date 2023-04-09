package com.gingermadfire.onlinestore.exchange.request;

import com.gingermadfire.onlinestore.persistence.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineSaveRequest {

    private Long goodsId;

    private Order order;

    private Integer count;

}
