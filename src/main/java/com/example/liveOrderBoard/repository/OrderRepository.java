package com.example.liveOrderBoard.repository;

import java.util.List;

import com.example.liveOrderBoard.entity.Order;
import com.example.liveOrderBoard.model.LiveBoardDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query(
        "SELECT NEW com.example.liveOrderBoard.model.LiveBoardDto(ord.price, SUM(ord.quantity), ord.type) "
        + "FROM Order ord GROUP BY ord.type, ord.price"
    )
    List<LiveBoardDto> getOrderSummary();
    
}
