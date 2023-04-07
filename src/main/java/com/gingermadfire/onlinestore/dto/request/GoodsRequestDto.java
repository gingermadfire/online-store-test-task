package com.gingermadfire.onlinestore.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsRequestDto {

    private String name;

    private BigDecimal price;
}
