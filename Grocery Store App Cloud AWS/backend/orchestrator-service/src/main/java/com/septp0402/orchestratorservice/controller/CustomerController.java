package com.septp0402.orchestratorservice.controller;

import com.septp0402.orchestratorservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/register")
    public String register(@RequestBody String registerDTO) {
        return customerService.register(registerDTO);
    }

    @PostMapping("/customer/login")
    public String login(@RequestBody String loginDTO) {
        return customerService.login(loginDTO);
    }

    @PostMapping("/customer/authenticate")
    public String authenticate(@RequestBody String json) {
        return customerService.authenticate(json);
    }

}
