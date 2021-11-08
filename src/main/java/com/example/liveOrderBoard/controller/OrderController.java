package com.example.liveOrderBoard.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import com.example.liveOrderBoard.model.LiveBoardDto;
import com.example.liveOrderBoard.model.OrderDto;
import com.example.liveOrderBoard.model.RegisterOrderRequest;
import com.example.liveOrderBoard.service.OrderService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;

    @PostMapping
    public CompletableFuture<OrderDto> registerNewOrder(@Valid @RequestBody final RegisterOrderRequest request) {
        return orderService.registerNewOrder(request.getOrder(), request.getUser());
    }

    @DeleteMapping("/{orderId}")
    public CompletableFuture<Void> cancelOrder(@PathVariable final long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping
    public CompletableFuture<List<LiveBoardDto>> getLiveOrderBoard() {
        return orderService.getLiveBoard();
    }
}
