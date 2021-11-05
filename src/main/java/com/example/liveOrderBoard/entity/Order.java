package com.example.liveOrderBoard.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "live_order")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    private int price;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private AppUser orderIssuer;

    @Enumerated(EnumType.STRING)
    private OrderType type;
}
