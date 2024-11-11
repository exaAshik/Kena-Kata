package com.ashik.kenakata.Service.cart;

import com.ashik.kenakata.Entity.Cart;

public interface CartService {

    Cart getCartByCustomerId(Long customerId);

    Cart addItemToCart(Long customerId, Long productId, Integer quantity);

    Cart updateCartItem(Long customerId, Long productId, Integer quantity);

    Cart removeItemFromCart(Long customerId, Long productId);

    Cart checkoutCart(Long customerId);
}
