package com.example.liveOrderBoard.model;

import com.example.liveOrderBoard.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterOrderRequest {
    private OrderDto order;
    private AppUser user;
}
