package com.example.liveOrderBoard.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.liveOrderBoard.entity.OrderType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderDto {
    private long id;

    @NotNull
    @Min(0)
    private int price;

    @NotNull
    @Min(0)
    private double quantity;

    @NotNull
    private OrderType type;
}
