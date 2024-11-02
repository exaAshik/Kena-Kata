package com.ashik.kenakata.Dto.User;

import lombok.Data;

import java.time.Instant;

@Data
public class SubscriptionDto {

    private Long id;

    private String subscriptionType;

    private Instant startDate;

    private Instant endDate;

    private Integer subcriptionPrice;

}
