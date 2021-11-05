package com.example.liveOrderBoard.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.liveOrderBoard.entity.AppUser;
import com.example.liveOrderBoard.model.LiveBoardDto;
import com.example.liveOrderBoard.model.OrderDto;

public interface OrderService {

    CompletableFuture<OrderDto> registerNewOrder(OrderDto orderDto, AppUser currentUser);

    CompletableFuture<Void> cancelOrder(long orderId);

    CompletableFuture<List<LiveBoardDto>> getLiveBoard();
}
