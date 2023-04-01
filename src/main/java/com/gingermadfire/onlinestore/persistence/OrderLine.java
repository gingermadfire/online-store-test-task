package com.gingermadfire.onlinestore.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JoinColumn(name = "order_id")
    private Long order_id;

    @Column(nullable = false)
    @JoinColumn(name = "goods_id")
    private long goods_id;

    @Column(nullable = false)
    private Integer count;

}
