package com.example.mapper;

import com.example.config.MapperConfig;
import com.example.dto.cart.CartItemRequestDto;
import com.example.dto.cart.ShoppingCartDto;
import com.example.model.CartItem;
import com.example.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartDto toDto(ShoppingCart cartForCurrentUser);

    @Mapping(target = "book.id", source = "bookId")
    CartItem toModel(CartItemRequestDto cartItemDto);
}
