package com.septp0402.customerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septp0402.customerservice.config.JWTGenerator;
import com.septp0402.customerservice.dto.*;
import com.septp0402.customerservice.models.AddressEntity;
import com.septp0402.customerservice.models.CustomerEntity;
import com.septp0402.customerservice.repository.AddressRepository;
import com.septp0402.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private AuthenticationManager authenticationManager;
    private CustomerRepository customerRepository;

    private AddressRepository addressRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public CustomerController(AuthenticationManager authenticationManager,
                              CustomerRepository customerRepository, AddressRepository addressRepository,
                              PasswordEncoder passwordEncoder,
                              JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String registerDTOString) {
        // initialise object mapper and register dto
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterDTO registerDTO;
        try {
            // try map input string to register dto
            registerDTO = objectMapper.readValue(registerDTOString, RegisterDTO.class);

            // if email already exists, return
            if(customerRepository.existsByUsername(registerDTO.getUserName())) {
                return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
            }

            // initialise and set the address entity
            AddressEntity address = new AddressEntity();
            address.setAddrStreetNum(registerDTO.getAddrStreetNum());
            address.setAddrStreet(registerDTO.getAddrStreet());
            address.setAddrCity(registerDTO.getAddrCity());
            address.setAddrPostcode(registerDTO.getAddrPostcode());
            address.setAddrState(registerDTO.getAddrState());
            address.setAddrCountry(registerDTO.getAddrCountry());

            // Check if the address already exists
            ArrayList<AddressEntity> existingAddresses = addressRepository.findByAddrStreetNumAndAddrStreetAndAddrCityAndAddrPostcodeAndAddrStateAndAddrCountry(
                    address.getAddrStreetNum(), address.getAddrStreet(), address.getAddrCity(),
                    address.getAddrPostcode(), address.getAddrState(), address.getAddrCountry()
            );

            // initialise saved address
            AddressEntity savedAddress;

            // If the address doesn't exist, save the new one
            if (existingAddresses.isEmpty()) {
                 savedAddress = addressRepository.save(address);
            } else {
                savedAddress = existingAddresses.get(0);
            }

            // initialise and set the customer entity
            CustomerEntity customer = new CustomerEntity();
            customer.setAddrID(savedAddress.getAddrID());
            customer.setFirstName(registerDTO.getFirstName());
            customer.setLastName(registerDTO.getLastName());
            customer.setUsername(registerDTO.getUserName());
            customer.setPhoneNumber(registerDTO.getPhoneNumber());
            customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            // add new customer to database
            customerRepository.save(customer);

            // return success message
            return new ResponseEntity<>("Registered successfully", HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorisationReturnDTO> login(@RequestBody String loginDTOString) {
        // initialise object mapper and login dto
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDTO loginDTO;
        try {
            // try map input string to login dto
            loginDTO = objectMapper.readValue(loginDTOString, LoginDTO.class);

            // authenticate user has correct credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

            // set security context so user doesn't have to keep signing in
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // generate token after successfully logging in
            String token = jwtGenerator.generateToken(authentication);

            // return required authorisation return dto
            Optional<CustomerEntity> customerEntity = customerRepository.findByUsername(loginDTO.getUserName());
            return new ResponseEntity<>(new AuthorisationReturnDTO(token, customerEntity.get().getId(), customerEntity.get().getUsername()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody String json) {
        return new ResponseEntity<>("Authenticated", HttpStatus.OK);
    }

    @PostMapping("/information/{cID}")
    public ResponseEntity<CustomerDTO> getCustomerInformation(@PathVariable int cID) {
        // retrieve the customer object by id
        CustomerEntity customer = customerRepository.findCustomerEntityById(cID);

        // retrieve the address object by id
        AddressEntity address = addressRepository.findAddressEntityByAddrID(customer.getAddrID());

        // initialise and set values for addressDTO object
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddrID(address.getAddrID());
        addressDTO.setAddrStreetNum(address.getAddrStreetNum());
        addressDTO.setAddrStreet(address.getAddrStreet());
        addressDTO.setAddrCity(address.getAddrCity());
        addressDTO.setAddrPostcode(address.getAddrPostcode());
        addressDTO.setAddrState(address.getAddrState());
        addressDTO.setAddrCountry(address.getAddrCountry());

        // initialise and set values for customerDTO object
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCID(customer.getId());
        customerDTO.setAddressDTO(addressDTO);
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
