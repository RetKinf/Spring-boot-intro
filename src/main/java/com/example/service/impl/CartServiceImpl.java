package com.example.service.impl;

import com.example.dto.cart.CartItemRequestDto;
import com.example.dto.cart.ShoppingCartDto;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.CartMapper;
import com.example.model.CartItem;
import com.example.model.ShoppingCart;
import com.example.model.User;
import com.example.repository.CartItemRepository;
import com.example.repository.ShoppingCartRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;

    @Override
    public ShoppingCartDto getCart() {
        ShoppingCart cartForCurrentUser = getCartForCurrentUser();
        System.out.println(cartForCurrentUser.getCartItems());
        return cartMapper.toDto(cartForCurrentUser);
    }

    @Override
    public void save(CartItemRequestDto cartItemDto) {
        ShoppingCart shoppingCart = getCartForCurrentUser();
        CartItem item = cartMapper.toModel(cartItemDto);
        item.setShoppingCart(shoppingCart);
        cartItemRepository.save(item);
    }

    @Override
    public void update(CartItemRequestDto cartItemDto, Long cartItemId) {
        CartItem cartItem = cartMapper.toModel(cartItemDto);
        cartItem.setId(cartItemId);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    private ShoppingCart getCartForCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String email = principal.getEmail();
        Long currentUserId = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + email
                ))
                .getId();
        return shoppingCartRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart not found by user ID " + currentUserId
                ));
    }
}
