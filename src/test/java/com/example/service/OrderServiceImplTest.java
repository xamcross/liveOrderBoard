package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.example.liveOrderBoard.entity.AppUser;
import com.example.liveOrderBoard.entity.Order;
import com.example.liveOrderBoard.entity.OrderType;
import com.example.liveOrderBoard.model.LiveBoardDto;
import com.example.liveOrderBoard.model.OrderDto;
import com.example.liveOrderBoard.repository.OrderRepository;
import com.example.liveOrderBoard.service.OrderService;
import com.example.liveOrderBoard.service.OrderServiceImpl;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void shouldRegisterNewOrder() throws Exception {
        final OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setPrice(5);
        orderDto.setQuantity(2.0);
        orderDto.setType(OrderType.SELL);

        final AppUser appUser = new AppUser();
        appUser.setName("Vasiliy");
        appUser.setId(1L);

        final Order newOrder = new Order();
        newOrder.setId(1L);
        newOrder.setPrice(5);
        newOrder.setQuantity(2.0);
        newOrder.setType(OrderType.SELL);
        newOrder.setOrderIssuer(appUser);

        when(orderRepository.save(any(Order.class))).thenReturn(newOrder);

        final CompletableFuture<OrderDto> registerNewOrder = orderService.registerNewOrder(orderDto, appUser);

        assertEquals(orderDto, registerNewOrder.get(200, TimeUnit.MILLISECONDS));
    }

    @Test
    public void shouldCancelOrder() {
        orderService.cancelOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldReturnLiveOrderBoard() throws Exception {
        final List<LiveBoardDto> liveBoard = Lists.list(new LiveBoardDto(23, 1.5, OrderType.SELL));
        when(orderRepository.getOrderSummary()).thenReturn(liveBoard);
        
        final CompletableFuture<List<LiveBoardDto>> liveBoarFuture = orderService.getLiveBoard();

        assertEquals(liveBoard, liveBoarFuture.get(200, TimeUnit.MILLISECONDS));
    }
}
