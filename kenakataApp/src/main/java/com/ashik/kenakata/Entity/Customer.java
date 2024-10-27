package com.ashik.kenakata.Entity;

import jakarta.persistence.Column;

public class Customer extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "points", nullable = false)
    private Integer points = 0;

    @Column(name = "points", nullable = false)
    private String role;
}