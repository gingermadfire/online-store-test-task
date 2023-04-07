package com.gingermadfire.onlinestore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineSaveRequestDto {

    private Long goodsId;

    private String client;

    private String address;

    private Integer count;

}
