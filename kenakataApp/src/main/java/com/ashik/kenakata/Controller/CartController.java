package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.Cart.CartDto;
import com.ashik.kenakata.Mapper.Cart.CartMapper;
import com.ashik.kenakata.Service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCart(@PathVariable Long customerId) {

        CartDto cartDto = CartMapper.INSTANCE.cartToCartDto(
                cartService.getCartByCustomerId(customerId)
        );

        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/{customerId}/add-item")
    public ResponseEntity<?> addItemToCart(@PathVariable Long customerId,
                                              @RequestParam Long productId,
                                              @RequestParam Integer quantity) {
        CartDto cartDto = CartMapper.INSTANCE.cartToCartDto(
                cartService.addItemToCart(customerId, productId, quantity)
        );
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/{customerId}/update-item")
    public ResponseEntity<?> updateCartItem(@PathVariable Long customerId,
                                               @RequestParam Long productId,
                                               @RequestParam Integer quantity) {
        CartDto cartDto = CartMapper.INSTANCE.cartToCartDto(
                cartService.updateCartItem(customerId, productId, quantity)
        );
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{customerId}/remove-item")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long customerId,
                                                   @RequestParam Long productId) {
        CartDto cartDto = CartMapper.INSTANCE.cartToCartDto(
                cartService.removeItemFromCart(customerId, productId)
        );
        return ResponseEntity.ok(cartDto);
    }


}
