package com.example.liveOrderBoard.model;

import com.example.liveOrderBoard.entity.OrderType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class LiveBoardDto {
    private int price;
    private double quantity;
    private OrderType type;
}
