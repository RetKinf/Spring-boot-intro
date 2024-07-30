package com.example.service.impl;

import com.example.dto.cart.CartItemRequestDto;
import com.example.dto.cart.ShoppingCartDto;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.BookMapper;
import com.example.mapper.CartMapper;
import com.example.model.Book;
import com.example.model.CartItem;
import com.example.model.ShoppingCart;
import com.example.model.User;
import com.example.repository.CartItemRepository;
import com.example.repository.ShoppingCartRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import jakarta.transaction.Transactional;
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
    private final BookMapper bookMapper;

    @Override
    public ShoppingCartDto getCart() {
        ShoppingCart cartForCurrentUser = getCartForCurrentUser();
        System.out.println(cartForCurrentUser.getCartItems());
        return cartMapper.toDto(cartForCurrentUser);
    }

    @Transactional
    @Override
    public ShoppingCartDto save(CartItemRequestDto cartItemDto) {
        Book book = bookMapper.bookFromId(cartItemDto.getBookId());
        ShoppingCart shoppingCart = getCartForCurrentUser();
        shoppingCart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(book.getId()))
                .findFirst()
                .ifPresentOrElse(item -> item.setQuantity(
                        item.getQuantity() + cartItemDto.getQuantity()),
                        () -> cartItemRepository.save(cartMapper.toModel(cartItemDto))
                );
        return cartMapper.toDto(getCartForCurrentUser());
    }

    @Transactional
    @Override
    public ShoppingCartDto update(CartItemRequestDto cartItemDto, Long cartItemId) {
        bookMapper.bookFromId(cartItemDto.getBookId());
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("Cart item with ID " + cartItemId + " not found");
        }
        CartItem cartItem = cartMapper.toModel(cartItemDto);
        cartItem.setId(cartItemId);
        cartItemRepository.save(cartItem);
        return cartMapper.toDto(getCartForCurrentUser());
    }

    @Transactional
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
