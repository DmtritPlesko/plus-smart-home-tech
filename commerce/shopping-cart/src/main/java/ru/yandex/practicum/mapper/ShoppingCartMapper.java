package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.model.ShoppingCart;

public interface ShoppingCartMapper {
    ShoppingCartDto toShoppingCartDto(ShoppingCart cart);

    ShoppingCart toShoppingCart(ShoppingCartDto shoppingCartDto);


}
