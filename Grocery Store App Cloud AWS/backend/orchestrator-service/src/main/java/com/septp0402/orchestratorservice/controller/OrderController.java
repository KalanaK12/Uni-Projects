package com.septp0402.orchestratorservice.controller;

import com.septp0402.orchestratorservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/cart")
    public String getShoppingCart(@RequestBody String displayCartStr) {
        return orderService.getShoppingCart(displayCartStr);
    }

    @PostMapping("/addItem")
    public String addShoppingItem(@RequestBody String addOrderItemDTOStr) {
        return orderService.addShoppingItem(addOrderItemDTOStr);
    }

    @DeleteMapping("/deleteItem")
    public String deleteShoppingItem(@RequestBody String deleteOrderItemStr) {
        return orderService.deleteShoppingItem(deleteOrderItemStr);
    }

    @DeleteMapping("/deleteCart")
    public String deleteShoppingCart(@RequestBody String deleteCartDTOStr) {
        return orderService.deleteShoppingCart(deleteCartDTOStr);
    }
}