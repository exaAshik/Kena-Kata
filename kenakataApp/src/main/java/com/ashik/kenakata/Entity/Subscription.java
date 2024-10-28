package com.ashik.kenakata.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription extends BaseEntity{

    @Column(name = "subscription_type", nullable = false)
    private String subscriptionType;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "subscription_price")
    private Integer subcriptionPrice;

}
