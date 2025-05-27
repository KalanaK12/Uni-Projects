package com.septp0402.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septp0402.orderservice.dto.AddOrderItemDTO;
import com.septp0402.orderservice.dto.DeleteOrderItemDTO;
import com.septp0402.orderservice.dto.CartDTO;
import com.septp0402.orderservice.model.OrderEntity;
import com.septp0402.orderservice.model.OrderItemEntity;
import com.septp0402.orderservice.repository.OrderItemRepository;
import com.septp0402.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping("/cart")
    public ArrayList<OrderItemEntity> getShoppingCart(@RequestBody String displayCartDTOStr) {
        // convert json to displayCartDTO
        ObjectMapper objectMapper = new ObjectMapper();
        CartDTO cartDTO;

        try {
            cartDTO = objectMapper.readValue(displayCartDTOStr, CartDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // get current order
        ArrayList<OrderEntity> orders = orderRepository.findOrderEntitiesByCidAndStatus(cartDTO.getCID(), "Initial");
        if (orders.size() != 0) {
            OrderEntity order = orders.get(0);

            // get a list of order items
            ArrayList<OrderItemEntity> orderItems = orderItemRepository.findOrderItemEntitiesByOrdid(order.getOrdid());

            // return list of items
            return orderItems;
        }
        else {
            // else return empty list
            return new ArrayList<>();
        }

    }

    @PostMapping("/addItem")
    public String addShoppingItem(@RequestBody String addOrderItemDTOStr) {
        // convert json to addOrderItemDTO
        ObjectMapper objectMapper = new ObjectMapper();
        AddOrderItemDTO addOrderItemDTO;

        try {
            addOrderItemDTO = objectMapper.readValue(addOrderItemDTOStr, AddOrderItemDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // initialise the order object
        OrderEntity order;

        // if an initial order does not exit, make a new order
        ArrayList<OrderEntity> orders = orderRepository.findOrderEntitiesByCidAndStatus(addOrderItemDTO.getCID(), "Initial");
        if (orders.size() == 0) {
            // make a new order object
            order = new OrderEntity();
            order.setCid(addOrderItemDTO.getCID());
            order.setStatus("Initial");

            // add new order to database
            orderRepository.save(order);

            // retrieve newly made order
            order = orderRepository.findOrderEntitiesByCidAndStatus(addOrderItemDTO.getCID(), "Initial").get(0);
        }
        // else set the returned order
        else {
            order = orders.get(0);
        }


        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrdid(order.getOrdid());
        orderItem.setPid(addOrderItemDTO.getPID());
        orderItem.setQuantity(addOrderItemDTO.getQuantity());
        orderItem.setTotalcost(addOrderItemDTO.getProductCost());

        // Check if item with same p_id already exists in the order
        OrderItemEntity existingItem = orderItemRepository.findOrderItemEntityByOrdidAndPid(order.getOrdid(), addOrderItemDTO.getPID());

        // initialise the save item
        OrderItemEntity savedItem;

        if(existingItem != null) {
            // Update quantity and total_cost of existing item
            existingItem.setQuantity(addOrderItemDTO.getQuantity());
            existingItem.setTotalcost(addOrderItemDTO.getProductCost());
            savedItem = orderItemRepository.save(existingItem);
        } else {

            // make new orderItem object
            orderItem = new OrderItemEntity();
            orderItem.setOrdid(order.getOrdid());
            orderItem.setPid(addOrderItemDTO.getPID());
            orderItem.setQuantity(addOrderItemDTO.getQuantity());
            orderItem.setTotalcost(addOrderItemDTO.getProductCost());

            // Create new OrderItemEntity and save to database
            savedItem = orderItemRepository.save(orderItem);
        }

        if (savedItem != null) {
            return "true";
        }
        else {
            return "false";
        }

    }

    @DeleteMapping("/deleteItem")
    @Transactional
    public String deleteShoppingItem(@RequestBody String deleteOrderItemStr) {
        // convert json to deleteOrderItemDTO
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteOrderItemDTO deleteOrderItemDTO;

        try {
            deleteOrderItemDTO = objectMapper.readValue(deleteOrderItemStr, DeleteOrderItemDTO.class);

            // get the order object
            // if an initial order does not exit, make a new order
            OrderEntity order = orderRepository.findOrderEntitiesByCidAndStatus(deleteOrderItemDTO.getCID(), "Initial").get(0);

            // get the order item object
            OrderItemEntity item = orderItemRepository.findOrderItemEntityByOrdidAndPid(order.getOrdid(), deleteOrderItemDTO.getPID());

            // delete the order item
            orderItemRepository.deleteById(item.getOrditemid());

            // return true for successful completion of
            return "true";

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }
    }
    
    @DeleteMapping("/deleteCart")
    @Transactional
    public String deleteShoppingCart(@RequestBody String deleteCartDTOStr) {
        // convert json to displayCartDTO
        ObjectMapper objectMapper = new ObjectMapper();
        CartDTO cartDTO;

        try {
            cartDTO = objectMapper.readValue(deleteCartDTOStr, CartDTO.class);

            OrderEntity order = orderRepository.findOrderEntitiesByCidAndStatus(cartDTO.getCID(), "Initial").get(0);

            // get a list of order items
            ArrayList<OrderItemEntity> orderItems = orderItemRepository.findOrderItemEntitiesByOrdid(order.getOrdid());
            
            // iterate through each order item and delete all of them
            for (OrderItemEntity item : orderItems) {
                orderItemRepository.deleteById(item.getOrditemid());
            }
            
            // after completion true to indicate success
            return "true";
            
        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }
    }
}