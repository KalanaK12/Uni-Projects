package com.septp0402.orchestratorservice.service;

import com.septp0402.orchestratorservice.proxy.CustomerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {
    private CustomerProxy customerProxy;

    @Autowired
    public CustomerService(CustomerProxy customerProxy) {
        this.customerProxy = customerProxy;
    }

    public String register(String registerDTO) {
        return customerProxy.register(registerDTO);
    }

    public String login(String loginDTO) {
        return customerProxy.login(loginDTO);
    }

    public String authenticate(String json) {
        return customerProxy.authenticate(json);
    }
}