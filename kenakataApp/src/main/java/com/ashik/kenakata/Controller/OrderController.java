package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.order.OrderDto;
import com.ashik.kenakata.Entity.Order;
import com.ashik.kenakata.Mapper.order.OrderMapper;
import com.ashik.kenakata.Service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {

        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        OrderDto createdOrder = OrderMapper.INSTANCE.orderToOrderDto(
                orderService.createOrder(order)
        );

        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {

        OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(
                orderService.getOrderById(orderId)
        );

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {

        OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(
                orderService.updateOrderStatus(orderId, status)
        );

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/payment-status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long orderId, @RequestParam String status) {

        OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(
                orderService.updatePaymentStatus(orderId, status)
        );

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
