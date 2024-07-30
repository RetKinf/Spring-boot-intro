package com.example.service;

import com.example.dto.cart.CartItemRequestDto;
import com.example.dto.cart.ShoppingCartDto;

public interface CartService {
    ShoppingCartDto getCart();

    void save(CartItemRequestDto itemCartDto);

    void update(CartItemRequestDto itemCartDto, Long cartItemId);

    void delete(Long cartItemId);
}
