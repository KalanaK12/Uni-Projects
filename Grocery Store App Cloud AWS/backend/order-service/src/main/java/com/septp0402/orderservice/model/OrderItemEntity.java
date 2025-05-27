package com.septp0402.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`order_item`")
@Data
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orditem_id")
    private int orditemid;

    @Column(name = "ord_id")
    private int ordid;

    @Column(name = "p_id")
    private int pid;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_cost")
    private double totalcost;
}