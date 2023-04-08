package com.gingermadfire.onlinestore.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsRequestDto {

    private String name;

    private BigDecimal price;
}
