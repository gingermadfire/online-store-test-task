package com.gingermadfire.onlinestore.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponseDto {

    private Long id;

    private String name;

    private BigDecimal price;

}
