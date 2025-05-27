package com.septp0402.orderservice.repository;

import com.septp0402.orderservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    ArrayList<OrderEntity> findOrderEntitiesByCidAndStatus(int cID, String status);

}