package com.ashik.kenakata.Service.Implementation.cart;

import com.ashik.kenakata.Entity.Cart;
import com.ashik.kenakata.Entity.CartItem;
import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.Product;
import com.ashik.kenakata.Repository.CartItemRepository;
import com.ashik.kenakata.Repository.CartRepository;
import com.ashik.kenakata.Repository.CustomerRepository;
import com.ashik.kenakata.Repository.ProductRepository;
import com.ashik.kenakata.Service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> createCartForCustomer(customerId));
    }

    @Override
    @Transactional
    public Cart addItemToCart(Long customerId, Long productId, Integer quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getQuantity() * product.getPrice());
        } else {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .price(quantity * product.getPrice())
                    .build();
            cart.getCartItems().add(cartItem);
        }

        updateTotalPrice(cart);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart updateCartItem(Long customerId, Long productId, Integer quantity) {
        Cart cart = getCartByCustomerId(customerId);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        cartItem.setQuantity(quantity);
        cartItem.setPrice(quantity * cartItem.getProduct().getPrice());
        updateTotalPrice(cart);

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart removeItemFromCart(Long customerId, Long productId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
        updateTotalPrice(cart);

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart checkoutCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.setCheckedOut(true);
        return cartRepository.save(cart);
    }

    private Cart createCartForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        Cart cart = Cart.builder()
                .customer(customer)
                .totalPrice(0.0)
                .isCheckedOut(false)
                .build();
        return cartRepository.save(cart);
    }

    private void updateTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
    }
}
