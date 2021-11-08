package com.example.liveOrderBoard.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "live_order")
public class Order {
    @Id
    @SequenceGenerator(name = "live_order_seq", initialValue = 1, sequenceName = "live_order_seq", allocationSize = 1)
    @GeneratedValue(generator = "live_order_seq")
    private long id;

    private int price;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private AppUser orderIssuer;

    @Enumerated(EnumType.STRING)
    private OrderType type;
}
