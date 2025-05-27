package com.septp0402.orchestratorservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service", url = "http://localhost:8082")
public interface CustomerProxy {
    @PostMapping("/customer/register")
    String register(@RequestBody String registerDTOString);

    @PostMapping("/customer/login")
    String login(@RequestBody String loginDTOString);

    @PostMapping("customer/authenticate")
    String authenticate(@RequestBody String json);

    @PostMapping("customer/information/{cID}")
    String getCustomerInformation(@PathVariable int cID);
}