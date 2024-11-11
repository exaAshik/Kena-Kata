package com.ashik.kenakata.Service.Implementation.order;

import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.Order;
import com.ashik.kenakata.Entity.OrderItem;
import com.ashik.kenakata.Entity.Product;
import com.ashik.kenakata.Repository.CustomerRepository;
import com.ashik.kenakata.Repository.OrderRepository;
import com.ashik.kenakata.Repository.ProductRepository;
import com.ashik.kenakata.Service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    @Transactional
    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        order.setCustomer(customer);

        order.setOrderStatus("PENDING");
        order.setPaymentStatus("UNPAID");
        order.setOrderedAt(Instant.now());

        double totalAmount = 0.0;
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            double itemPrice = product.getPrice() * orderItem.getQuantity();
            totalAmount += itemPrice;

            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setDiscount(0.0);
        }
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order updatePaymentStatus(Long orderId, String status) {
        Order order = getOrderById(orderId);
        order.setPaymentStatus(status);
        return orderRepository.save(order);
    }
}
