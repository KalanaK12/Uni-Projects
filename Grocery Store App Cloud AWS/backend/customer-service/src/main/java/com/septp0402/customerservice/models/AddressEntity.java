package com.septp0402.customerservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    private int addrID;

    @Column(name = "addr_strtnum")
    private int addrStreetNum;

    @Column(name = "addr_street")
    private String addrStreet;

    @Column(name = "addr_city")
    private String addrCity;

    @Column(name = "addr_postcode")
    private String addrPostcode;

    @Column(name = "addr_state")
    private String addrState;

    @Column(name = "addr_country")
    private String addrCountry;
}
