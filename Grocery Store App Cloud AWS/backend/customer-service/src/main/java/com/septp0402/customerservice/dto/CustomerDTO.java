package com.septp0402.customerservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDTO {
    private int cID;
    private AddressDTO addressDTO;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
}
