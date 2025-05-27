package com.septp0402.customerservice.dto;

import lombok.Data;

@Data
public class AuthorisationReturnDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int customerID;
    private String customerUsername;

    public AuthorisationReturnDTO(String accessToken, int customerID, String customerUsername) {
        this.accessToken = accessToken;
        this.customerID = customerID;
        this.customerUsername = customerUsername;
    }
}
