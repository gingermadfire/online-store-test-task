package com.gingermadfire.onlinestore.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;

    private String client;

    private String address;

}
