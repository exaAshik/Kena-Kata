package com.ashik.kenakata.Service.order;

import com.ashik.kenakata.Entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByCustomerId(Long customerId);

    Order updateOrderStatus(Long orderId, String status);

    Order updatePaymentStatus(Long orderId, String status);

}
