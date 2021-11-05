package com.example.liveOrderBoard.model;

import com.example.liveOrderBoard.entity.OrderType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LiveBoardDto {
    private int price;
    private double quantity;
    private OrderType type;
}
