package com.ashik.kenakata.Dto.order;

import com.ashik.kenakata.Dto.User.CustomerDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDto {

    private List<OrderItemDto> orderItems;

    private String orderStatus;

    private String paymentStatus;

    private String shippingAddress;

    private String billingAddress;

    private Double totalAmount;

    private Instant orderedAt;

    private Instant shippedAt;

    private Instant deliveredAt;

    private CustomerDto customer;
}
