package com.ashik.kenakata.Dto.User;


import lombok.Data;

@Data
public class CustomerCreateDto {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    private String city;

    private Integer points = 0;

    private String role;

    private String profileImageUrl;

    private SubscriptionDto subscription;

}
