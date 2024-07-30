package com.example.controller;

import com.example.dto.cart.CartItemRequestDto;
import com.example.dto.cart.ShoppingCartDto;
import com.example.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart management", description = "Endpoints for managing carts")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get a shopping cart", description = "Get a all items of cart")
    public ShoppingCartDto getCart() {
        return cartService.getCart();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a book to cart", description = "Add a book to cart")
    public void addItem(@RequestBody @Valid CartItemRequestDto itemCartDto) {
        cartService.save(itemCartDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update quantity", description = "Update quantity")
    public void updateItem(
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemRequestDto cartItemDto
    ) {
        cartService.update(cartItemDto, cartItemId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Remove a book from cart",
            description = "Remove a book from a cart"
    )
    public void deleteItem(@PathVariable Long cartItemId) {
        cartService.delete(cartItemId);
    }
}
