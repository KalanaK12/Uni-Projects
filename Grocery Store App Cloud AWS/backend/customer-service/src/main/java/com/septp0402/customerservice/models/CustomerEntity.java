package com.septp0402.customerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;

    @Column(name = "addr_id")
    private int addrID;

    @Column(name = "c_fname", length = 255)
    private String firstName;

    @Column(name = "c_lname", length = 255)
    private String lastName;

    @Column(name = "c_email", length = 255)
    private String username;

    @Column(name = "c_phnum", length = 255)
    private String phoneNumber;

    @Column(name = "c_passwd", length = 255)
    private String password;

}
