package com.septp0402.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddOrderItemDTO {
    
    @JsonProperty("cID")
    private int cID;

    @JsonProperty("pID")
    private int pID;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("productCost")
    private double productCost;
}