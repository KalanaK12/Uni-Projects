package com.septp0402.customerservice.repository;

import com.septp0402.customerservice.dto.AddressDTO;
import com.septp0402.customerservice.models.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    ArrayList<AddressEntity> findByAddrStreetNumAndAddrStreetAndAddrCityAndAddrPostcodeAndAddrStateAndAddrCountry(
            Integer addrStreetNum, String addrStreet, String addrCity,
            String addrPostcode, String addrState, String addrCountry
    );

    AddressEntity findAddressEntityByAddrID(int addrID);
}
