package com.septp0402.orderservice.repository;

import com.septp0402.orderservice.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
    ArrayList<OrderItemEntity> findOrderItemEntitiesByOrdid(int ordID);

    OrderItemEntity findOrderItemEntityByOrdidAndPid(int ordID, int pID);

}