package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.example.liveOrderBoard.controller.OrderController;
import com.example.liveOrderBoard.entity.AppUser;
import com.example.liveOrderBoard.entity.OrderType;
import com.example.liveOrderBoard.model.LiveBoardDto;
import com.example.liveOrderBoard.model.OrderDto;
import com.example.liveOrderBoard.model.RegisterOrderRequest;
import com.example.liveOrderBoard.service.OrderService;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    public void setup() {
        orderController = new OrderController(orderService);
    }

    @Test
    public void shouldGetLiveBoard() throws Exception {
        final List<LiveBoardDto> list = Lists.list(new LiveBoardDto(23, 1.5, OrderType.SELL));
        when(orderService.getLiveBoard()).thenReturn(CompletableFuture.completedFuture(list));

        final OrderController orderController = new OrderController(orderService);
        final CompletableFuture<List<LiveBoardDto>> liveOrderBoard = orderController.getLiveOrderBoard();
        assertEquals(liveOrderBoard.get(200L, TimeUnit.MILLISECONDS),
                Lists.list(new LiveBoardDto(23, 1.5, OrderType.SELL)));
    }

    @Test
    public void shouldCancelExistingOrder() throws Exception {
        when(orderService.cancelOrder(anyLong())).thenReturn(CompletableFuture.completedFuture(null));

        orderController.cancelOrder(1L);
    }

    @Test
    public void shouldRegisterNewOrder() throws Exception {
        final OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setPrice(5);
        orderDto.setQuantity(2.0);
        orderDto.setType(OrderType.SELL);
        when(orderService.registerNewOrder(any(OrderDto.class), any(AppUser.class))).thenReturn(CompletableFuture.completedFuture(orderDto));

        final RegisterOrderRequest request = new RegisterOrderRequest();
        request.setOrder(new OrderDto());
        request.setUser(new AppUser());
        final CompletableFuture<OrderDto> registerNewOrder = orderController.registerNewOrder(request);

        assertEquals(orderDto, registerNewOrder.get(200, TimeUnit.MILLISECONDS));
    }
}
