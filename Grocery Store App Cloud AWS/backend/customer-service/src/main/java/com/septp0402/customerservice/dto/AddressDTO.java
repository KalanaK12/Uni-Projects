package com.septp0402.customerservice.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private int addrID;
    private int addrStreetNum;
    private String addrStreet;
    private String addrCity;
    private String addrPostcode;
    private String addrState;
    private String addrCountry;
}
