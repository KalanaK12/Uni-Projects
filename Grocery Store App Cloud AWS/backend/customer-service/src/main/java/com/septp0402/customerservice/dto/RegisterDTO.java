package com.septp0402.customerservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String password;
    private int addrStreetNum;
    private String addrStreet;
    private String addrCity;
    private String addrPostcode;
    private String addrState;
    private String addrCountry;
}
