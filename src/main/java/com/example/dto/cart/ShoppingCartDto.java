package com.example.dto.cart;

import com.example.model.CartItem;
import java.util.Set;
import lombok.Data;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItem> cartItems;
}
