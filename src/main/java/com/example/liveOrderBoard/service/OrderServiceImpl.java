package com.example.liveOrderBoard.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import com.example.liveOrderBoard.entity.AppUser;
import com.example.liveOrderBoard.entity.Order;
import com.example.liveOrderBoard.entity.OrderType;
import com.example.liveOrderBoard.model.LiveBoardDto;
import com.example.liveOrderBoard.model.OrderDto;
import com.example.liveOrderBoard.repository.OrderRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Async
    @Transactional
    public CompletableFuture<OrderDto> registerNewOrder(final OrderDto orderDto, final AppUser currentUser) {
        log.info("User: {} registers new order: {}", currentUser, orderDto);

        final Order newOrderEntity = convertToEntity(orderDto, currentUser);

        final Order savedOrder = orderRepository.save(newOrderEntity);

        log.info("New order with id: {} has been registered", savedOrder.getId());

        return CompletableFuture.completedFuture(convertToDto(savedOrder));
    }

    @Override
    @Async
    @Transactional
    public CompletableFuture<Void> cancelOrder(final long orderId) {
        log.info("Cancelling order with id: {}", orderId);

        orderRepository.deleteById(orderId);

        log.info("Cancelled order with id: {}", orderId);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<List<LiveBoardDto>> getLiveBoard() {
        log.info("Live Order Board summary has been requested");

        final List<LiveBoardDto> liveBoard = orderRepository.getOrderSummary();

        Collections.sort(liveBoard, getOrderComparator());

        log.info("Current Live Order Board summary: {}", liveBoard);

        return CompletableFuture.completedFuture(liveBoard);
    }

    private Comparator<LiveBoardDto> getOrderComparator() {
        return (order1, order2) -> {
            final OrderType order1Type = order1.getType();
            final OrderType order2Type = order2.getType();
            if (order1Type != order2Type)
                return order1Type.compareTo(order2Type);
            if (order1Type == OrderType.SELL) {
                return Integer.compare(order1.getPrice(), order2.getPrice());
            }
            return Integer.compare(order2.getPrice(), order1.getPrice());
        };
    }

    private Order convertToEntity(final OrderDto orderDto, final AppUser user) {
        final Order orderEntity = new Order();
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setQuantity(orderDto.getQuantity());
        orderEntity.setType(orderDto.getType());
        orderEntity.setOrderIssuer(user);
        return orderEntity;
    }

    private OrderDto convertToDto(final Order orderEntity) {
        final OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setQuantity(orderEntity.getQuantity());
        orderDto.setType(orderEntity.getType());
        return orderDto;
    }

}
