package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.request.ChangeProductQuantityRequest;

import java.util.Map;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart(String username);

    ShoppingCartDto updateShoppingCart(String username, Map<String, Integer> products);

    void deleteCart(String username);

    ShoppingCartDto remove(String username, Map<String, Long> products);

    ShoppingCartDto changeQuantity(String username, ChangeProductQuantityRequest changeProductQuantityRequest);

    BookedProductsDto booking(String username);
}
