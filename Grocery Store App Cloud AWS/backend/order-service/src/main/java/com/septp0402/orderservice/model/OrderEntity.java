package com.septp0402.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private int ordid;

    @Column(name = "c_id")
    private int cid;

    @Column(name = "status", length = 255)
    private String status;

    @Column(name = "total_cost")
    private double totalcost;
}