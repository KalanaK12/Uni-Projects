package com.septp0402.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartDTO {
   
    @JsonProperty("cID")
    private int cID;
}