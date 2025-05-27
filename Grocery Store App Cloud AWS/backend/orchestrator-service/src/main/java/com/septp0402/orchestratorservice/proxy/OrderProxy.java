package com.septp0402.orchestratorservice.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderProxy {
    @PostMapping("/order/cart")
    String getShoppingCart(@RequestBody String displayCartStr);

    @PostMapping("/order/addItem")
    String addShoppingItem(@RequestBody String addOrderItemDTOStr);

    @DeleteMapping("/order/deleteItem")
    String deleteShoppingItem(@RequestBody String deleteOrderItemStr);

    @DeleteMapping("/order/deleteCart")
    String deleteShoppingCart(@RequestBody String deleteCartDTOStr);
}