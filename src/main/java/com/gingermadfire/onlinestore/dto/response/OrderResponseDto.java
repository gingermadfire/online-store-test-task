package com.gingermadfire.onlinestore.dto.response;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto {

    private Long id;

    private String client;

    private Instant date;

    private String address;

}
