package com.ashik.kenakata.Dto.Cart;

import com.ashik.kenakata.Dto.User.CustomerDto;
import com.ashik.kenakata.Entity.CartItem;
import com.ashik.kenakata.Entity.Customer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private CustomerDto customer;

    private List<CartItemDto> cartItems;

    private Double totalPrice = 0.0;

    private boolean isCheckedOut ;
}
