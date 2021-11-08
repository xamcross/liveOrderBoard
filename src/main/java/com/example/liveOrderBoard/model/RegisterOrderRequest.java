package com.example.liveOrderBoard.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.liveOrderBoard.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterOrderRequest {
    @NotNull
    @Valid
    private OrderDto order;
    private AppUser user;
}
