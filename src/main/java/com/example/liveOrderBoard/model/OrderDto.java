package com.example.liveOrderBoard.model;

import com.example.liveOrderBoard.entity.OrderType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {
    private long id;
    private int price;
    private double quantity;
    private OrderType type;
}
